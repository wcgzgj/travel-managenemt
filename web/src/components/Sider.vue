<template>
    <a-layout-sider width="200" style="background: #fff">
        <a-menu
                mode="inline"
                :style="{ height: '100%', borderRight: 0 }"
        >
            <a-sub-menu key="sub1">
                <template #title>
                  <span>
                    旅游管理
                  </span>
                </template>
                <a-menu-item key="1">
                    <router-link to="/user-list">
                        用户管理
                    </router-link>
                </a-menu-item>
                <a-menu-item key="2">
                    <router-link to="/category-list">
                        分类管理
                    </router-link>
                </a-menu-item>
                <a-menu-item key="3">
                    <router-link to="/route-list">
                        旅游线路
                    </router-link>
                </a-menu-item>
                <a-menu-item key="4">
                    <router-link to="/seller-list">
                        旅游公司
                    </router-link>
                </a-menu-item>
            </a-sub-menu>

        </a-menu>
    </a-layout-sider>
</template>

<script>
    export default {
        name: "Sider"
    }
</script>

<style scoped>

</style>