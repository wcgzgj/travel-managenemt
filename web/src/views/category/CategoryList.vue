<template>
    <a-breadcrumb style="margin: 16px 0"></a-breadcrumb>
    <a-layout-content
            :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
        <h1 style="font-size: 30px">分类管理</h1>


        <a-button type="primary" >
            <router-link to="/category-add">
                添加
            </router-link>
        </a-button>

        <br/>
        <br/>

        <a-table
                :columns="columns"
                :data-source="categorys"
        >
            <template #name="{ text }">
                <a>{{ text }}</a>
            </template>

            <template #action="{ record }">
                      <span>
                        <!--使用 router-link 跳转，带上参数-->
                        <a-button type="primary">
                          <!--跳转到修改页面-->
                            <!--传入账单信息-->
                          <router-link :to="'/category-update?categoryId='+record.id">
                            修改
                          </router-link>
                        </a-button>
                      </span>
                &nbsp;
                <span>
                            <!--使用 restful 风格的删除-->
                            <a-button type="danger" @click="deleteById(record.id)">删除</a-button>
                        </span>
            </template>
        </a-table>


    </a-layout-content>
</template>

<script>
    import {ref} from "vue";
    import {useRoute} from "vue-router";
    import axios from "axios";
    import {message} from "ant-design-vue";
    import router from "@/router";
    import {onMounted} from "@vue/runtime-core";

    const columns = [
        {
            title: '名称',
            dataIndex: 'cname',
            key: 'cname',
        },
        {
            title: '操作',
            key: 'action',
            slots: {
                customRender: 'action',
            },
        },
    ];
    export default {
        name: "CategoryList",
        setup() {



            /**
             * 搜索函数
             */
            const categorys = ref([]);
            const onSearch = () => {
                axios.get("/category/list",{
                    params:{
                        pageNum:1,
                        pageSize:20
                    }
                }).then(resp=> {
                    const data = resp.data;
                    if (data.success) {
                        console.log("查询数据成功");
                        console.log("查询出的数据条数为:"+data.content.list.length);
                        categorys.value=data.content.list;
                        console.log("传来的total值为:"+data.content.total);
                    } else {
                        message.error(data.message);
                    }
                })
            }



            /**
             * onMounted 基本格式
             */
            onMounted(()=>{
                onSearch(1);
            });

            return {
                categorys,
                columns,
            };
        },
    }
</script>

<style scoped>

</style>