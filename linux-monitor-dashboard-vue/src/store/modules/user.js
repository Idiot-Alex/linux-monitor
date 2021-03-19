import { USER_KEY } from '../../utils/const';

const user = {
  state: {
    userInfo: JSON.parse(localStorage.getItem(USER_KEY))
  },
  mutations: {
    setUserInfo(state, userInfo) {
      state.userInfo = userInfo
    },
  },
  actions: {
    // login
    login({commit}, data) {
      return new Promise((resolve) => {
        commit('setUserInfo', data);
        localStorage.setItem(USER_KEY, JSON.stringify(res.data));
        resolve();
      });
    }
  },
  getters: {
    getUserInfo(state) {
      return state.userInfo;
    }
  }
}

export default user
