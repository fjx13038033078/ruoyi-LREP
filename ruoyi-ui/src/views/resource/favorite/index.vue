<template>
  <div class="app-container">
    <el-table :data="favoriteList" v-loading="loading" style="width: 100%" border>
      <el-table-column label="收藏ID" prop="favoriteId" align="center"></el-table-column>
      <el-table-column label="资源名称" prop="resourceName" align="center"></el-table-column>
      <el-table-column label="收藏时间" prop="favoriteTime" align="center"></el-table-column>
      <el-table-column label="收藏用户" prop="userName" align="center"></el-table-column>
      <el-table-column label="操作" align="center" width="260px">
        <template #default="scope">
          <el-button type="info" size="mini" @click="handleView(scope.row)">查看资源</el-button>
          <el-button type="danger" size="mini" @click="cancelFavorite(scope.row.favoriteId)">取消收藏</el-button>
        </template>
      </el-table-column>
    </el-table>

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
          <FileUpload v-model="viewResourceForm.fileName" :is-show-tip="false" :disabled="true"></FileUpload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="viewDialogVisible = false">关闭</el-button>
      </div>
    </el-dialog>

    <!-- 分页组件 -->
    <pagination
      v-show="totalFavorites > 0"
      :total="totalFavorites"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="fetchFavorites"
    />
  </div>
</template>

<script>
import {listAllFavorites, deleteFavorite} from '@/api/resource/favorite';
import {getResourceById} from "@/api/resource/resource";

export default {
  data() {
    return {
      loading: true,
      favoriteList: [],
      totalFavorites: 0,
      viewDialogVisible: false,
      queryParams: {
        pageNum: 1,
        pageSize: 10
      },
      viewResourceForm: {}
    };
  },
  created() {
    this.fetchFavorites();
  },
  methods: {
    // 获取收藏列表
    fetchFavorites() {
      this.loading = true;
      listAllFavorites(this.queryParams).then(response => {
        this.favoriteList = response.rows;
        this.totalFavorites = response.total;
        this.loading = false;
      });
    },
    // 取消收藏
    cancelFavorite(favoriteId) {
      this.$confirm('确定要取消该收藏吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteFavorite(favoriteId).then(() => {
          this.$message.success('取消收藏成功');
          this.fetchFavorites();
        });
      });
    },
    handleView(row) {
      getResourceById(row.resourceId).then(response => {
        this.viewResourceForm = { ...response.data }
        this.viewDialogVisible = true
      })
    }
  }
};
</script>

<style scoped>
</style>
