import { login, logout, getInfo } from '@/api/login'
import { getToken, setToken, removeToken } from '@/utils/auth'

const user = {
  namespaced: true,
  state: {
    token: getToken(),
    name: '',
    avatar: '',
    roles: [],
    permissions: []
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_PERMISSIONS: (state, permissions) => {
      state.permissions = permissions
    }
  },

  actions: {
    // 登录
    login({ commit }, userInfo) {
      const username = userInfo.username.trim()
      const password = userInfo.password
      const code = userInfo.code
      const uuid = userInfo.uuid
      return new Promise((resolve, reject) => {
        login(username, password, code, uuid).then(res => {
          if (!res || !res.token) {
            return reject('登录失败，未获取到有效token');
          }
          setToken(res.token)
          commit('SET_TOKEN', res.token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 获取用户信息
    getInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        getInfo().then(res => {
          if (!res) {
            return reject('获取用户信息失败');
          }
          
          const user = res.user || {};
          // 使用默认头像URL而不是本地文件
          const avatar = user.avatar || "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png";
          
          // 设置角色和权限
          const roles = res.roles || [];
          const permissions = res.permissions || [];
          
          if (roles && roles.length > 0) {
            commit('SET_ROLES', roles);
            commit('SET_PERMISSIONS', permissions);
          } else {
            commit('SET_ROLES', ['ROLE_DEFAULT']);
          }
          
          commit('SET_NAME', user.userName || '');
          commit('SET_AVATAR', avatar);
          resolve(res);
        }).catch(error => {
          reject(error);
        });
      });
    },
    
    // 退出系统
    logout({ commit, state }) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          commit('SET_PERMISSIONS', [])
          removeToken()
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}

export default user 