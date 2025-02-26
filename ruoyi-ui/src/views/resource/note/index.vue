<template>
  <div class="app-container">
    <!-- 新增笔记按钮 -->
    <el-row :gutter="20" class="mb-20" style="margin-bottom: 20px;">
      <el-col>
        <el-button type="primary" @click="handleAdd" v-hasPermi="['note:add']">新增笔记</el-button>
      </el-col>
    </el-row>

    <!-- 笔记列表 -->
    <el-table :data="noteList" v-loading="loading" border style="width: 100%">
      <el-table-column label="笔记ID" prop="noteId" align="center"></el-table-column>
      <el-table-column label="笔记标题" prop="title" align="center"></el-table-column>
      <el-table-column label="笔记内容" prop="content" align="center">
        <template #default="scope">
          <span>{{ scope.row.content.slice(0, 5) }}...</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="400" fixed="right">
        <template #default="scope">
          <el-button type="info" size="mini" @click="handleView(scope.row)">查看</el-button>
          <el-button type="primary" size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button type="danger" size="mini" @click="handleDelete(scope.row.noteId)">删除</el-button>
          <el-button type="warning" size="mini" @click="openRemarkDialog(scope.row)">评论</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="fetchNotes"
    />

    <!-- 添加/编辑笔记对话框 -->
    <el-dialog :visible.sync="dialogVisible" :title="dialogTitle" width="600px">
      <el-form :model="noteForm" label-width="100px" ref="noteFormRef" :rules="rules">
        <el-form-item label="笔记标题" prop="title">
          <el-input v-model="noteForm.title"></el-input>
        </el-form-item>
        <el-form-item label="笔记内容" prop="content">
          <el-input v-model="noteForm.content" type="textarea" :rows="3"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submitForm">确定</el-button>
      </div>
    </el-dialog>

    <!-- 查看笔记详情对话框 -->
    <el-dialog :visible.sync="viewDialogVisible" title="笔记详情" width="600px">
      <el-form :model="viewNoteForm" label-width="100px">
        <el-form-item label="笔记标题">
          <el-input v-model="viewNoteForm.title" readonly></el-input>
        </el-form-item>
        <el-form-item label="笔记内容">
          <el-input v-model="viewNoteForm.content" type="textarea" :rows="3" readonly></el-input>
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
import { listAllNotes, addNote, updateNote, deleteNote, getNoteById } from '@/api/resource/note'
import { listRemarksByNoteId, addRemark, deleteRemark } from '@/api/resource/remark'

export default {
  data() {
    return {
      loading: true,
      noteList: [],
      total: 0,
      queryParams: {
        pageNum: 1,
        pageSize: 10
      },
      dialogVisible: false,
      dialogTitle: '',
      noteForm: {
        noteId: null,
        title: '',
        content: ''
      },
      viewDialogVisible: false,
      viewNoteForm: {
        title: '',
        content: ''
      },
      remarkDialogVisible: false,
      selectedNote: {},
      remarks: [],
      newRemark: '',
      rules: {
        title: [{ required: true, message: '请输入笔记标题', trigger: 'blur' }],
        content: [{ required: true, message: '请输入笔记内容', trigger: 'blur' }]
      }
    }
  },
  created() {
    this.fetchNotes()
  },
  methods: {
    // 获取笔记列表
    fetchNotes() {
      this.loading = true
      listAllNotes().then(response => {
        this.noteList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 打开新增笔记对话框
    handleAdd() {
      this.resetForm()
      this.dialogTitle = '新增笔记'
      this.dialogVisible = true
    },
    // 编辑笔记
    handleEdit(row) {
      getNoteById(row.noteId).then(response => {
        this.noteForm = { ...response.data }
        this.dialogTitle = '编辑笔记'
        this.dialogVisible = true
      })
    },
    // 提交表单
    submitForm() {
      this.$refs.noteFormRef.validate(valid => {
        if (valid) {
          if (this.noteForm.noteId) {
            updateNote(this.noteForm).then(() => {
              this.$message.success('更新成功')
              this.fetchNotes()
              this.dialogVisible = false
            })
          } else {
            addNote(this.noteForm).then(() => {
              this.$message.success('新增成功')
              this.fetchNotes()
              this.dialogVisible = false
            })
          }
        }
      })
    },
    // 删除笔记
    handleDelete(noteId) {
      this.$confirm('确定要删除该笔记吗？', '提示', { type: 'warning' }).then(() => {
        deleteNote(noteId).then(() => {
          this.$message.success('删除成功')
          this.fetchNotes()
        })
      })
    },
    // 查看笔记详情
    handleView(row) {
      getNoteById(row.noteId).then(response => {
        this.viewNoteForm = { ...response.data }
        this.viewDialogVisible = true
      })
    },
    // 打开评论对话框并加载评论列表
    openRemarkDialog(row) {
      this.selectedNote = row
      listRemarksByNoteId(row.noteId).then(response => {
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
        noteId: this.selectedNote.noteId,
        content: reviewRemark,
        type: 2
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
      this.noteForm = {
        noteId: null,
        title: '',
        content: ''
      }
      this.$refs.noteFormRef?.resetFields()
    }
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
