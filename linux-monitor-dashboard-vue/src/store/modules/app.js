
const app = {
  state: {
    isCollapse: false,
  },
  mutations: {
    setIsCollapse(state, isCollapse) {
      state.isCollapse = isCollapse;
    }
  },
  actions: {
    // collapse
    collapse({commit, state}) {
      return new Promise((resolve) => {
        const isCollapse = !state.isCollapse
        commit('setIsCollapse', isCollapse);
        resolve();
      });
    }
  },
  getters: {
    getIsCollapse(state) {
      return state.isCollapse;
    }
  }
}

export default app;
