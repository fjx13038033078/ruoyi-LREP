<template>
  <div class="home-container">
    <el-container>
      <el-header>
        <div class="header-left">
          <div class="logo">
            <i class="el-icon-s-platform logo-icon"></i>
            <span class="logo-text">学习资源分享平台</span>
          </div>
        </div>
        <div class="header-center">
          <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
            background-color="#ffffff"
            text-color="#333"
            active-text-color="#409EFF"
          >
            <el-menu-item index="1">首页</el-menu-item>
            <el-menu-item index="2">资源分类</el-menu-item>
            <el-menu-item index="3">最新上传</el-menu-item>
            <el-menu-item index="4">热门推荐</el-menu-item>
            <el-menu-item index="5">学习社区</el-menu-item>
          </el-menu>
        </div>
        <div class="header-right">
          <el-input
            placeholder="搜索资源..."
            prefix-icon="el-icon-search"
            v-model="searchText"
            class="search-input"
            @keyup.enter.native="handleSearch"
          >
          </el-input>
          <div class="user-info" v-if="token">
            <el-dropdown trigger="click" @command="handleCommand">
              <span class="el-dropdown-link">
                <el-avatar :size="32" :src="userAvatar"></el-avatar>
                <span class="username">{{ userName }}</span>
                <i class="el-icon-arrow-down el-icon--right"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="userCenter">个人中心</el-dropdown-item>
                <el-dropdown-item command="myResource">我的资源</el-dropdown-item>
                <el-dropdown-item command="myCollection">我的收藏</el-dropdown-item>
                <el-dropdown-item command="setting">设置</el-dropdown-item>
                <el-dropdown-item divided command="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
          <div v-else>
            <router-link to="/login">
              <el-button type="primary" size="small">登录</el-button>
            </router-link>
            <router-link to="/register">
              <el-button size="small">注册</el-button>
            </router-link>
          </div>
        </div>
      </el-header>
      
      <el-main>
        <div class="welcome-section">
          <div class="welcome-content">
            <h1>欢迎来到学习资源分享平台</h1>
            <p>在这里，您可以找到丰富的学习资源，包括视频教程、电子书籍、学习笔记和实践项目等。</p>
            <el-button type="primary" size="medium" @click="exploreResources">开始探索</el-button>
          </div>
        </div>
        
        <div class="resource-section">
          <div class="section-title">
            <h2>热门资源分类</h2>
            <router-link to="/categories">查看全部</router-link>
          </div>
          <el-row :gutter="20">
            <el-col :span="6" v-for="(category, index) in categories" :key="index">
              <el-card shadow="hover" class="category-card">
                <div class="category-icon">
                  <i :class="category.icon"></i>
                </div>
                <div class="category-name">{{ category.name }}</div>
                <div class="category-count">{{ category.count }}个资源</div>
              </el-card>
            </el-col>
          </el-row>
        </div>
        
        <div class="resource-section">
          <div class="section-title">
            <h2>最新上传</h2>
            <router-link to="/resources/latest">查看全部</router-link>
          </div>
          <el-row :gutter="20">
            <el-col :span="6" v-for="(resource, index) in latestResources" :key="index">
              <el-card shadow="hover" class="resource-card">
                <div class="resource-cover">
                  <img :src="resource.cover" :alt="resource.title" />
                </div>
                <div class="resource-info">
                  <div class="resource-title">{{ resource.title }}</div>
                  <div class="resource-meta">
                    <span class="resource-author">
                      <i class="el-icon-user"></i> {{ resource.author }}
                    </span>
                    <span class="resource-views">
                      <i class="el-icon-view"></i> {{ resource.views }}
                    </span>
                  </div>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </div>
        
        <div class="resource-section">
          <div class="section-title">
            <h2>推荐资源</h2>
            <router-link to="/resources/recommended">查看全部</router-link>
          </div>
          <el-row :gutter="20">
            <el-col :span="6" v-for="(resource, index) in recommendedResources" :key="index">
              <el-card shadow="hover" class="resource-card">
                <div class="resource-cover">
                  <img :src="resource.cover" :alt="resource.title" />
                </div>
                <div class="resource-info">
                  <div class="resource-title">{{ resource.title }}</div>
                  <div class="resource-meta">
                    <span class="resource-author">
                      <i class="el-icon-user"></i> {{ resource.author }}
                    </span>
                    <span class="resource-views">
                      <i class="el-icon-view"></i> {{ resource.views }}
                    </span>
                  </div>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </div>
      </el-main>
      
      <el-footer>
        <div class="footer-content">
          <div class="footer-links">
            <a href="#">关于我们</a>
            <a href="#">使用条款</a>
            <a href="#">隐私政策</a>
            <a href="#">联系我们</a>
            <a href="#">帮助中心</a>
          </div>
          <div class="footer-copyright">
            © 2023 学习资源分享平台 All Rights Reserved
          </div>
        </div>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Home',
  data() {
    return {
      activeIndex: '1',
      searchText: '',
      defaultAvatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      categories: [
        { name: '编程开发', icon: 'el-icon-s-opportunity', count: 128 },
        { name: '设计创意', icon: 'el-icon-picture', count: 85 },
        { name: '外语学习', icon: 'el-icon-chat-dot-round', count: 64 },
        { name: '职业考证', icon: 'el-icon-medal', count: 76 }
      ],
      latestResources: [
        { title: 'Java从入门到精通教程', author: '张三', views: 1234, cover: 'https://via.placeholder.com/300x200?text=Java教程' },
        { title: 'Vue.js实战项目开发', author: '李四', views: 986, cover: 'https://via.placeholder.com/300x200?text=Vue.js教程' },
        { title: 'Python数据分析与可视化', author: '王五', views: 768, cover: 'https://via.placeholder.com/300x200?text=Python教程' },
        { title: 'UI设计从入门到精通', author: '赵六', views: 520, cover: 'https://via.placeholder.com/300x200?text=UI设计' }
      ],
      recommendedResources: [
        { title: 'Spring Boot实战教程', author: '张三', views: 2345, cover: 'https://via.placeholder.com/300x200?text=Spring+Boot' },
        { title: 'React Native移动应用开发', author: '李四', views: 1865, cover: 'https://via.placeholder.com/300x200?text=React+Native' },
        { title: '机器学习入门与实践', author: '王五', views: 1596, cover: 'https://via.placeholder.com/300x200?text=机器学习' },
        { title: '数据结构与算法分析', author: '赵六', views: 1378, cover: 'https://via.placeholder.com/300x200?text=数据结构' }
      ]
    }
  },
  computed: {
    ...mapGetters([
      'token',
      'avatar',
      'name',
      'roles'
    ]),
    // 确保有可用的头像
    userAvatar() {
      return this.avatar || this.defaultAvatar;
    },
    // 确保有可用的用户名
    userName() {
      return this.name || '用户';
    }
  },
  created() {
    // 如果有token但没有用户信息，尝试获取用户信息
    if (this.token && (!this.name || !this.roles.length)) {
      this.$store.dispatch('user/getInfo');
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    handleCommand(command) {
      if (command === 'logout') {
        this.$store.dispatch('user/logout').then(() => {
          this.$router.push('/login');
        });
      } else if (command === 'userCenter') {
        this.$router.push('/user/profile');
      } else if (command === 'myResource') {
        this.$router.push('/user/resources');
      } else if (command === 'myCollection') {
        this.$router.push('/user/collections');
      } else if (command === 'setting') {
        this.$router.push('/user/settings');
      }
    },
    handleSearch() {
      this.$router.push({
        path: '/search',
        query: { keyword: this.searchText }
      });
    },
    exploreResources() {
      this.$router.push('/resources');
    }
  }
}
</script>

<style lang="scss" scoped>
.home-container {
  height: 100%;
  
  .el-container {
    height: 100%;
    display: flex;
    flex-direction: column;
  }

  .el-header {
    display: flex;
    align-items: center;
    justify-content: space-between;
    height: 60px;
    background-color: #fff;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
    padding: 0 20px;
    position: relative;
    z-index: 1000;
  }

  .header-left {
    display: flex;
    align-items: center;

    .logo {
      display: flex;
      align-items: center;
      
      .logo-icon {
        font-size: 40px;
        color: #409EFF;
        margin-right: 10px;
      }
      
      .logo-text {
        font-size: 18px;
        font-weight: 600;
        color: #409EFF;
      }
    }
  }

  .header-center {
    flex: 1;
    display: flex;
    justify-content: center;
    
    .el-menu {
      border-bottom: none;
    }
    
    .el-menu-item {
      font-size: 15px;
    }
  }

  .header-right {
    display: flex;
    align-items: center;
    
    .search-input {
      width: 200px;
      margin-right: 20px;
    }
    
    .user-info {
      cursor: pointer;
      
      .el-dropdown-link {
        display: flex;
        align-items: center;
        
        .username {
          margin: 0 5px;
          color: #333;
        }
      }
    }
    
    .el-button {
      margin-left: 10px;
    }
  }

  .el-main {
    padding: 0;
    background-color: #f5f7fa;
  }
  
  .welcome-section {
    height: 400px;
    background-image: linear-gradient(to right, rgba(64, 158, 255, 0.9), rgba(83, 82, 237, 0.9));
    background-size: cover;
    background-position: center;
    display: flex;
    justify-content: center;
    align-items: center;
    color: #fff;
    text-align: center;
    
    .welcome-content {
      max-width: 800px;
      padding: 0 20px;
      
      h1 {
        font-size: 36px;
        margin-bottom: 20px;
      }
      
      p {
        font-size: 18px;
        margin-bottom: 30px;
        line-height: 1.6;
      }
      
      .el-button {
        padding: 12px 25px;
        font-size: 16px;
      }
    }
  }
  
  .resource-section {
    padding: 40px;
    
    .section-title {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 20px;
      
      h2 {
        font-size: 24px;
        font-weight: 600;
        color: #333;
        margin: 0;
      }
      
      a {
        color: #409EFF;
        font-size: 14px;
      }
    }
    
    .category-card {
      height: 160px;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      cursor: pointer;
      transition: all 0.3s;
      
      &:hover {
        transform: translateY(-5px);
      }
      
      .category-icon {
        font-size: 50px;
        color: #409EFF;
        margin-bottom: 15px;
      }
      
      .category-name {
        font-size: 18px;
        font-weight: 500;
        margin-bottom: 5px;
      }
      
      .category-count {
        font-size: 14px;
        color: #909399;
      }
    }
    
    .resource-card {
      cursor: pointer;
      overflow: hidden;
      transition: all 0.3s;
      
      &:hover {
        transform: translateY(-5px);
      }
      
      .resource-cover {
        height: 160px;
        overflow: hidden;
        
        img {
          width: 100%;
          height: 100%;
          object-fit: cover;
          transition: transform 0.3s;
        }
      }
      
      &:hover .resource-cover img {
        transform: scale(1.05);
      }
      
      .resource-info {
        padding: 15px;
        
        .resource-title {
          font-size: 16px;
          font-weight: 500;
          margin-bottom: 10px;
          overflow: hidden;
          text-overflow: ellipsis;
          white-space: nowrap;
        }
        
        .resource-meta {
          display: flex;
          justify-content: space-between;
          font-size: 12px;
          color: #909399;
          
          i {
            margin-right: 3px;
          }
        }
      }
    }
  }

  .el-footer {
    background-color: #2c3e50;
    color: #fff;
    padding: 30px 40px;
    
    .footer-content {
      max-width: 1200px;
      margin: 0 auto;
    }
    
    .footer-links {
      display: flex;
      justify-content: center;
      margin-bottom: 20px;
      
      a {
        color: #ddd;
        margin: 0 15px;
        
        &:hover {
          color: #fff;
        }
      }
    }
    
    .footer-copyright {
      text-align: center;
      color: #aaa;
      font-size: 12px;
    }
  }
}
</style> 