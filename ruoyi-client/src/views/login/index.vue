<template>
  <div class="login-container">
    <div class="login-box">
      <div class="login-left">
        <div class="login-image"></div>
      </div>
      <div class="login-form">
        <div class="login-title">
          <h2>学习资源分享平台</h2>
          <p>Learning Resource Sharing</p>
        </div>
        <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form-content">
          <el-form-item prop="username">
            <el-input
              v-model="loginForm.username"
              type="text"
              placeholder="账号"
              prefix-icon="el-icon-user"
            >
            </el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input
              v-model="loginForm.password"
              type="password"
              placeholder="密码"
              prefix-icon="el-icon-lock"
              @keyup.enter.native="handleLogin"
            >
            </el-input>
          </el-form-item>
          <el-form-item prop="code" v-if="captchaEnabled">
            <el-input
              v-model="loginForm.code"
              placeholder="验证码"
              style="width: 63%"
              prefix-icon="el-icon-key"
              @keyup.enter.native="handleLogin"
            >
            </el-input>
            <div class="login-code">
              <img :src="codeUrl" @click="getCode" class="login-code-img" />
            </div>
          </el-form-item>
          <el-checkbox v-model="loginForm.rememberMe" style="margin:0 0 25px 0;">记住密码</el-checkbox>
          <el-form-item style="margin-bottom:15px;">
            <el-button
              :loading="loading"
              type="primary"
              style="width:100%;"
              @click.native.prevent="handleLogin"
            >
              <span v-if="!loading">登 录</span>
              <span v-else>登 录 中...</span>
            </el-button>
          </el-form-item>
          <div class="login-options">
            <router-link to="/register" class="register-link">注册账号</router-link>
            <a href="javascript:;" class="forget-pwd">忘记密码</a>
          </div>
        </el-form>
        <div class="login-footer">
          © 2023 学习资源分享平台 All Rights Reserved
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getCodeImg } from "@/api/login";

export default {
  name: "Login",
  data() {
    return {
      codeUrl: "",
      loginForm: {
        username: "",
        password: "",
        rememberMe: false,
        code: "",
        uuid: ""
      },
      loginRules: {
        username: [
          { required: true, trigger: "blur", message: "请输入您的账号" }
        ],
        password: [
          { required: true, trigger: "blur", message: "请输入您的密码" }
        ],
        code: [{ required: true, trigger: "blur", message: "请输入验证码" }]
      },
      loading: false,
      captchaEnabled: true,
      redirect: undefined
    };
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true
    }
  },
  created() {
    this.getCode();
  },
  methods: {
    getCode() {
      getCodeImg().then(res => {
        this.captchaEnabled = res.captchaEnabled === undefined ? true : res.captchaEnabled;
        if (this.captchaEnabled) {
          this.codeUrl = "data:image/gif;base64," + res.img;
          this.loginForm.uuid = res.uuid;
        }
      });
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true;
          this.$store
            .dispatch("user/login", this.loginForm)
            .then(() => {
              this.$router.push({ path: this.redirect || "/" });
              this.loading = false;
            })
            .catch(() => {
              this.loading = false;
              this.getCode();
            });
        }
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background: #f5f5f5;
  background-image: linear-gradient(to bottom right, #6a11cb, #2575fc);
}

.login-box {
  display: flex;
  width: 960px;
  height: 560px;
  border-radius: 10px;
  box-shadow: 0 20px 50px rgba(0, 0, 0, 0.2);
  overflow: hidden;
  background: #fff;
}

.login-left {
  flex: 1;
  background: #eef6ff;
  position: relative;
}

.login-image {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: linear-gradient(to right bottom, #6a11cb, #2575fc);
  background-size: cover;
  background-position: center;
}

.login-form {
  flex: 1;
  padding: 50px;
  display: flex;
  flex-direction: column;
}

.login-title {
  text-align: center;
  margin-bottom: 40px;
  
  h2 {
    font-size: 26px;
    font-weight: 600;
    color: #333;
    margin-bottom: 10px;
  }
  
  p {
    font-size: 16px;
    color: #999;
    margin: 0;
  }
}

.login-form-content {
  flex: 1;
}

.login-footer {
  text-align: center;
  font-size: 12px;
  color: #999;
  margin-top: 20px;
}

.login-code {
  float: right;
  width: 33%;
  height: 40px;
  
  .login-code-img {
    width: 100%;
    height: 100%;
    cursor: pointer;
    border-radius: 4px;
  }
}

.login-options {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
  font-size: 14px;
  
  .register-link {
    color: #409EFF;
  }
  
  .forget-pwd {
    color: #909399;
  }
}

::v-deep .el-input__inner {
  height: 44px;
  border-radius: 4px;
}

::v-deep .el-form-item {
  margin-bottom: 25px;
}
</style> 