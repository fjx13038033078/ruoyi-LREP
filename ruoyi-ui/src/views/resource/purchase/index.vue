<template>
  <div>
    <div class="app-container">
      <!-- 购买记录列表 -->
      <el-table :data="purchaseList" v-loading="loading" style="width: 100%" border>
        <el-table-column label="购买ID" prop="purchaseId" align="center"></el-table-column>
        <el-table-column label="购买用户名" prop="username" align="center"></el-table-column>
        <el-table-column label="课程名称" prop="courseName" align="center"></el-table-column>
        <el-table-column label="购买时间" prop="purchaseTime" align="center"></el-table-column>
      </el-table>

      <!-- 分页 -->
      <pagination
        v-show="total > 0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="fetchPurchaseList"
      />
    </div>
  </div>
</template>

<script>
import { listAllPurchases } from '@/api/resource/purchase';

export default {
  data() {
    return {
      loading: true,
      purchaseList: [],  // 用于存储购买记录数据
      total: 0,
      queryParams: {
        pageNum: 1,
        pageSize: 10
      },
    };
  },
  created() {
    this.fetchPurchaseList();
  },
  methods: {
    // 获取所有购买记录
    fetchPurchaseList() {
      this.loading = true;
      listAllPurchases().then(response => {
        this.purchaseList = response.rows;
        this.total = response.total;
        this.loading = false;
      }).catch(() => {
        this.loading = false;
        this.$message.error('加载购买记录失败');
      });
    }
  }
};
</script>

<style scoped>
/* 样式相关内容 */
</style>
