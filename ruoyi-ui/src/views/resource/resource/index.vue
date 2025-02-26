<template>
  <div class="app-container">
    <!-- 新增资源按钮 -->
    <el-row :gutter="20" class="mb-20" style="margin-bottom: 20px;">
      <el-col :span="24" :xs="24">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
          <el-form-item label="用户名称" prop="keyword">
            <el-input
              v-model="queryParams.keyword"
              placeholder="请输入关键词"
              clearable
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col>
        <el-button type="primary" @click="handleAdd" v-hasPermi="['resource:resource:add']">新增资源</el-button>
      </el-col>
    </el-row>

    <!-- 资源列表 -->
    <el-table :data="resourceList" v-loading="loading" border style="width: 100%">
      <el-table-column label="资源ID" prop="resourceId" align="center"></el-table-column>
      <el-table-column label="资源名称" prop="resourceName" align="center"></el-table-column>
      <el-table-column label="资源描述" prop="description" align="center">
        <template #default="scope">
          <span>{{ scope.row.description.slice(0, 5) }}...</span>
        </template>
      </el-table-column>
      <el-table-column label="上传者" prop="userName" align="center"></el-table-column>
      <el-table-column label="操作" align="center" width="400" fixed="right">
        <template #default="scope">
          <el-button type="info" size="mini" @click="handleView(scope.row)" v-hasPermi="['resource:resource:view']">查看</el-button>
          <el-button type="primary" size="mini" @click="handleEdit(scope.row)" v-hasPermi="['resource:resource:edit']">编辑</el-button>
          <el-button type="danger" size="mini" @click="handleDelete(scope.row.resourceId)" v-hasPermi="['resource:resource:delete']">删除</el-button>
          <el-button type="warning" size="mini" @click="openRemarkDialog(scope.row)" >评论</el-button>
          <el-button type="success" size="mini" @click="handleAddFavorite(scope.row)" >收藏</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="fetchResources"
    />

    <!-- 添加/编辑资源对话框 -->
    <el-dialog :visible.sync="dialogVisible" :title="dialogTitle" width="600px">
      <el-form :model="resourceForm" label-width="100px" ref="resourceFormRef" :rules="rules">
        <el-form-item label="资源名称" prop="resourceName">
          <el-input v-model="resourceForm.resourceName"></el-input>
        </el-form-item>
        <el-form-item label="资源描述" prop="description">
          <el-input v-model="resourceForm.description" type="textarea" :rows="3"></el-input>
        </el-form-item>
        <el-form-item label="资源上传">
          <FileUpload v-model="resourceForm.fileName"></FileUpload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submitForm">确定</el-button>
      </div>
    </el-dialog>

    <!-- 查看资源详情对话框 -->
    <el-dialog :visible.sync="viewDialogVisible" title="资源详情" width="600px">
      <el-form :model="viewResourceForm" label-width="100px">
        <el-form-item label="资源名称">
          <el-input v-model="viewResourceForm.resourceName" readonly></el-input>
        </el-form-item>
        <el-form-item label="资源描述">
          <el-input v-model="viewResourceForm.description" type="textarea" :rows="3" readonly></el-input>
        </el-form-item>
        <el-form-item label="附件查看">
          <FileUpload v-model="viewResourceForm.fileName" :is-show-tip="false" :disabled="true" :resource-id="viewResourceForm.resourceId"></FileUpload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="viewDialogVisible = false">关闭</el-button>
      </div>
    </el-dialog>

    <!-- 评论对话框 -->
    <el-dialog :visible.sync="remarkDialogVisible" title="评论详情">
      <div v-if="remarks.length === 0" class="no-remark">
        <p class="center-text">暂无评论</p>
      </div>
      <div v-for="(remark, index) in remarks" :key="index" class="remark-item">
        <div class="remark-info">
          <span class="remark-user">评论人：{{ remark.username }}</span>
          <span class="remark-time">评论时间：{{ remark.remarkTime }}</span>
        </div>
        <div class="remark-content">
          <p>{{ remark.content }}</p>
          <el-button type="danger" size="mini" @click="handleDeleteRemark(remark.remarkId)">删除</el-button>
        </div>
      </div>
      <div class="divider"></div>
      <div class="new-remark">
        <el-input v-model="newRemark" placeholder="请输入评论内容"></el-input>
        <el-button type="success" size="mini" @click="handleAddRemark">发送评论</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listAllResources, addResource, updateResource, deleteResource, getResourceById } from '@/api/resource/resource'
import { listRemarksByResourceId, addRemark, deleteRemark } from '@/api/resource/remark'
import {addFavorite} from "@/api/resource/favorite";

export default {
  data() {
    return {
      loading: true,
      // 显示搜索条件
      showSearch: true,
      resourceList: [],
      total: 0,
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        keyword: undefined
      },
      dialogVisible: false,
      dialogTitle: '',
      resourceForm: {
        resourceId: null,
        resourceName: '',
        description: '',
        fileName: ''
      },
      viewDialogVisible: false,
      viewResourceForm: {
        resourceName: '',
        resourceDescription: ''
      },
      remarkDialogVisible: false,
      selectedResource: {},
      remarks: [],
      newRemark: '',
      rules: {
        resourceName: [{ required: true, message: '请输入资源名称', trigger: 'blur' }],
        resourceDescription: [{ required: true, message: '请输入资源描述', trigger: 'blur' }]
      }
    }
  },
  created() {
    this.fetchResources()
  },
  methods: {
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.fetchResources();
    },
    resetQuery(){
      this.queryParams.keyword = ''
      this.fetchResources()
    },
    // 获取资源列表
    fetchResources() {
      this.loading = true
      listAllResources(this.queryParams.keyword).then(response => {
        this.resourceList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 打开新增资源对话框
    handleAdd() {
      this.resetForm()
      this.dialogTitle = '新增资源'
      this.dialogVisible = true
    },
    // 编辑资源
    handleEdit(row) {
      getResourceById(row.resourceId).then(response => {
        this.resourceForm = { ...response.data }
        this.dialogTitle = '编辑资源'
        this.dialogVisible = true
      })
    },
    // 提交表单
    submitForm() {
      this.$refs.resourceFormRef.validate(valid => {
        if (valid) {
          if (this.resourceForm.resourceId) {
            updateResource(this.resourceForm).then(() => {
              this.$message.success('更新成功')
              this.fetchResources()
              this.dialogVisible = false
            })
          } else {
            addResource(this.resourceForm).then(() => {
              this.$message.success('新增成功')
              this.fetchResources()
              this.dialogVisible = false
            })
          }
        }
      })
    },
    // 删除资源
    handleDelete(resourceId) {
      this.$confirm('确定要删除该资源吗？', '提示', { type: 'warning' }).then(() => {
        deleteResource(resourceId).then(() => {
          this.$message.success('删除成功')
          this.fetchResources()
        })
      })
    },
    // 查看资源详情
    handleView(row) {
      getResourceById(row.resourceId).then(response => {
        this.viewResourceForm = { ...response.data }
        this.viewDialogVisible = true
      })
    },
    // 打开评论对话框并加载评论列表
    openRemarkDialog(row) {
      this.selectedResource = row
      listRemarksByResourceId(row.resourceId).then(response => {
        this.remarks = response.data
        this.remarkDialogVisible = true
      })
    },
    // 添加评论
    handleAddRemark() {
      const reviewRemark = this.newRemark.trim()
      if (!reviewRemark) {
        this.$message.error('请输入评论内容')
        return
      }
      const remarkData = {
        resourceId: this.selectedResource.resourceId,
        content: reviewRemark,
        type: 1
      }
      addRemark(remarkData).then(() => {
        this.$message.success('评论成功')
        this.newRemark = ''
        this.remarkDialogVisible = false
      })
    },
    // 删除评论
    handleDeleteRemark(remarkId) {
      deleteRemark(remarkId).then(() => {
        this.$message.success('删除成功')
        this.remarkDialogVisible = false
      })
    },
    // 重置表单
    resetForm() {
      this.resourceForm = {
        resourceId: null,
        resourceName: '',
        resourceDescription: ''
      }
      this.$refs.resourceFormRef?.resetFields()
    },
    // 收藏资源
    handleAddFavorite(row) {
      const favoriteData = {
        resourceId: row.resourceId
      };
      addFavorite(favoriteData).then(() => {
        this.$message.success('资源收藏成功');
      });
    },
  }
}
</script>

<style scoped>
.center-text {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  font-size: 15px; /* 设置字体大小为15像素 */
}

/* 评论对话框样式 */
.remark-dialog {
  max-height: 800px; /* 设置最大高度 */
  overflow-y: auto; /* 添加纵向滚动条 */
}

/* 评论样式 */
.remark-item {
  margin-bottom: 20px;
  border: 1px solid #eee;
  padding: 10px;
}

/* 调整评论人和评论时间的间距 */
.remark-info {
  margin-bottom: 5px; /* 增加评论人和评论时间之间的垂直间距 */
  border-bottom: 1px solid #ccc; /* 添加底部边框，实现表格线效果 */
  padding-bottom: 15px; /* 调整底部边框与评论内容之间的间距 */
}

.remark-time {
  margin-left: 300px; /* 增加评论时间与评论人之间的水平间距 */
}

/* 新增评论输入框样式 */
.new-remark {
  margin-top: 20px; /* 设置输入框与上方元素的垂直间距 */
}

/* 调整发送评论按钮的垂直间距 */
.new-remark .el-button {
  margin-top: 10px; /* 设置按钮与上方元素的垂直间距 */
}

/* 分割线样式 */
.divider {
  border-top: 3px solid #ccc;
  margin-top: 20px; /* 调整分割线上方的间距 */
  margin-bottom: 20px; /* 调整分割线下方的间距 */
}
</style>
