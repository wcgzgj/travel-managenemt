<template>
    <a-breadcrumb style="margin: 16px 0"></a-breadcrumb>
    <a-layout-content
            :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >

        <h1 style="font-size: 30px">用户管理</h1>

        姓名:
        <a-input v-model:value="searchUser.name" placeholder="用户姓名" style="width: 140px"/>

        &nbsp;
        &nbsp;

        邮箱:
        <a-input v-model:value="searchUser.email" placeholder="用户邮箱" style="width: 140px"/>

        &nbsp;
        &nbsp;


        <a-button type="primary" :onclick="onSearch">查询</a-button>
        &nbsp;
        &nbsp;
        <a-button type="primary" :onclick="reset">重置</a-button>
        &nbsp;
        &nbsp;
        <a-button type="primary" >
            <router-link to="/user-add">
                添加
            </router-link>
        </a-button>

        <br/>
        <br/>

        <!--2、table 的模板-->
        <!--table的列信息，由 :columns="columns" 决定，:columns="columns"是 js 中定义的列数组-->
        <!--table的分页信息，由 pagination 决定，pagination 在 js 中已经定义-->
        <a-table
                :columns="columns"
                :data-source="users"
                :pagination="pagination"
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
                          <router-link :to="'/user-update?userId='+record.id">
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
    import { defineComponent,ref } from 'vue';
    import {Tool} from "@/util/Tool";
    import { message } from 'ant-design-vue';
    import {computed} from "@vue/reactivity";
    import store from "@/store";
    import axios from 'axios';
    import {useRoute} from "vue-router";
    import router from '../../router';
    import {onMounted} from "@vue/runtime-core";


    const columns = [
        {
            title: '姓名',
            dataIndex: 'name',
            key: 'name',
        },
        {
            title: '性别',
            dataIndex: 'sex',
            key: 'sex',
        },
        {
            title: '出生日期',
            dataIndex: 'birthdayStr',
            key: 'birthdayStr',
        },
        {
            title: '电话',
            key: 'telephone',
            dataIndex: 'telephone',
        },
        {
            title: '邮箱',
            key: 'email',
            dataIndex: 'email',
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
        name: "List",

        setup() {
            /**
             * 页面加载的参数
             */
            const loading = ref(false);

            /**
             * 获取 url 中信息的工具
             */
            const route = useRoute();

            /**
             * 用户查询信息
             */
            const searchUser = ref({
                name:"",
                email:"",
                pageNum:1,
                pageSize:5
            });

            /**
             * 重置按钮
             * 点击以后，会去重置输入框中的内容
             */
            const reset = () => {
                searchUser.value.name="";
                searchUser.value.email="";
            }

            /**
             * 分页的基本格式
             */
            const pagination = ref( {
                onChange: page => {
                    console.log("点击的页码为:"+page);
                    searchUser.value.pageNum=page;
                    onSearch();
                },
                pageSize: 5,
                total: 100
            });



            /**
             * 搜索函数
             */
            const users = ref([]);
            const onSearch = () => {
                axios.get("/user/list",{
                    params:{
                        pageNum:searchUser.value.pageNum,
                        pageSize:searchUser.value.pageSize,
                        name:searchUser.value.name,
                        email:searchUser.value.email
                    }
                }).then(resp=> {
                    const data = resp.data;
                    if (data.success) {
                        console.log("查询数据成功");
                        console.log("查询出的数据条数为:"+data.content.list.length);
                        users.value=data.content.list;
                        console.log("传来的total值为:"+data.content.total);
                        // 修改分页信息中的 total 信息
                        pagination.value.total=data.content.total;
                    } else {
                        message.error(data.message);
                    }
                })
            }


            /**
             * axios get 的基本格式
             * 传输参数，需要使用 params 形式
             */
            axios.get("/uri",{
                params:{
                    // pageNum:bill.value.pageNum,
                    // pageSize:bill.value.pageSize,
                    // startDate:bill.value.startDate,
                    // endDate:bill.value.endDate,
                    // title:bill.value.title,
                    // typeId:bill.value.typeId
                }
            }).then(resp=> {
                const data = resp.data;
                if (data.success) {
                    console.log("查询数据成功");
                    console.log("查询出的数据条数为:"+data.content.list.length);
                    // bills.value=data.content.list; 为表格/列表中的数据赋值
                    console.log("传来的total值为:"+data.content.total);
                    // 修改分页信息中的 total 信息
                    pagination.value.total=data.content.total;
                } else {
                    message.error(data.message);
                }
            })


            /**
             * axios post 基本格式
             * 传输参数，必须使用封装好了的数据类型（如下面的 bill.value）
             * 后端接口，要加上 @RequestBody 注解（将 json 形式的数据自动转换）
             */
            axios.post("/uri",
                // bill.value
            ).then(resp=> {
                const data = resp.data;
                if (data.success) {
                    message.success("修改成功");
                    // 修改成功，返回首页
                    router.push("/");
                } else {
                    message.error(data.message);
                }
            })


            /**
             * onMounted 基本格式
             */
            onMounted(()=>{
                onSearch(1);
            });

            return {
                loading,
                searchUser,
                pagination,
                reset,
                users,
                columns,
                onSearch
            };
        },
    }
</script>

<style scoped>

</style>