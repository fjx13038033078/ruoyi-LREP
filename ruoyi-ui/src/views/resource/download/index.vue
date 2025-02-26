<template>
  <div>
    <div class="app-container">
      <!-- 下载记录列表 -->
      <el-table :data="downloadList" v-loading="loading" style="width: 100%" border>
        <el-table-column label="下载ID" prop="downloadId" align="center"></el-table-column>
        <el-table-column label="资源名称" prop="resourceName" align="center"></el-table-column>
        <el-table-column label="文件名称" prop="fileName" align="center"></el-table-column>
        <el-table-column label="文件类型" prop="type" align="center"></el-table-column>
        <el-table-column label="下载时间" prop="downloadTime" align="center"></el-table-column>

        <!-- 操作栏 -->
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.row.downloadId)" v-hasPermi="['resource:download:delete']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <pagination
        v-show="total > 0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="fetchDownloadList"
      />
    </div>
  </div>
</template>

<script>
import { listAllDownloads, deleteDownload } from '@/api/resource/download';

export default {
  data() {
    return {
      loading: true,
      downloadList: [],  // 用于存储下载记录数据
      total: 0,
      queryParams: {
        pageNum: 1,
        pageSize: 10
      },
    };
  },
  created() {
    this.fetchDownloadList();
  },
  methods: {
    // 获取所有下载记录
    fetchDownloadList() {
      this.loading = true;
      listAllDownloads().then(response => {
        this.downloadList = response.rows;
        this.total = response.total;
        this.loading = false;
      }).catch(() => {
        this.loading = false;
        this.$message.error('加载下载记录失败');
      });
    },

    // 删除下载记录
    handleDelete(downloadId) {
      this.$confirm('此操作将永久删除该下载记录, 是否继续?', '警告', {
        type: 'warning'
      }).then(() => {
        deleteDownload(downloadId).then(() => {
          this.$message.success('下载记录删除成功');
          this.fetchDownloadList();  // 删除后重新加载列表
        }).catch(() => {
          this.$message.error('删除下载记录失败');
        });
      }).catch(() => {});
    }
  }
};
</script>

<style scoped>
/* 样式相关内容 */
</style>
