<template>
    <a-breadcrumb style="margin: 16px 0"></a-breadcrumb>
    <a-layout-content
            :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >

        <h1 style="font-size: 30px">路线管理</h1>

        线路名称:
        <a-input v-model:value="searchRoute.rname" placeholder="线路名称" style="width: 140px"/>

        &nbsp;
        &nbsp;

        线路介绍:
        <a-input v-model:value="searchRoute.routeintroduce" placeholder="线路介绍" style="width: 140px"/>

        &nbsp;
        &nbsp;

        所属分类:
        <a-select
                v-model:value="searchRoute.cid"
                style="width: 140px"
                @focus="focus"
                ref="select"
        >
            <a-select-option v-for="category in categorys" :value="category.cid">{{category.cname}}</a-select-option>
        </a-select>


        &nbsp;
        &nbsp;

        旅游公司:
        <a-select
                v-model:value="searchRoute.sid"
                style="width: 140px"
                @focus="focus"
                ref="select"
        >
            <a-select-option v-for="seller in sellers" :value="seller.sid">{{seller.sname}}</a-select-option>
        </a-select>

        &nbsp;
        &nbsp;


        <a-button type="primary" :onclick="onSearch">查询</a-button>
        &nbsp;
        &nbsp;
        <a-button type="primary" :onclick="reset">重置</a-button>
        &nbsp;
        &nbsp;
        <a-button type="primary" >
            <router-link to="/route-add">
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
                :data-source="routes"
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
                          <router-link :to="'/route-update?routeId='+record.id">
                            修改
                          </router-link>
                        </a-button>
                      </span>
                &nbsp;
                        <span>
                            <!--使用 restful 风格的删除-->
                            <a-button type="danger" @click="deleteById(record.id)">删除</a-button>
                        </span>

                &nbsp;
                <span>
                        <!--使用 router-link 跳转，带上参数-->
                        <a-button type="primary">
                          <!--跳转到修改页面-->
                            <!--传入账单信息-->
                          <router-link :to="'/route-image?routeId='+record.id">
                            图片修改
                          </router-link>
                        </a-button>
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
            title: '线路名称',
            dataIndex: 'rname',
            key: 'rname',
        },
        {
            title: '所属分类',
            dataIndex: 'cname',
            key: 'cname',
        },
        {
            title: '旅游公司',
            dataIndex: 'sname',
            key: 'sname',
        },
        {
            title: '价格',
            key: 'price',
            dataIndex: 'price',
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
        name: "RouteList",

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
            const searchRoute = ref({
                /**
                 * private String rname;
                    private String routeintroduce;
                 */
                rname:"",
                routeintroduce:"",
                cid:"",
                sid:"",
                pageNum:1,
                pageSize:5
            });

            /**
             * 重置按钮
             * 点击以后，会去重置输入框中的内容
             */
            const reset = () => {
                searchRoute.value.rname="";
                searchRoute.value.routeintroduce="";
                searchRoute.value.sid="";
                searchRoute.value.cid="";
            }

            /**
             * 分页的基本格式
             */
            const pagination = ref( {
                onChange: page => {
                    console.log("点击的页码为:"+page);
                    searchRoute.value.pageNum=page;
                    onSearch();
                },
                pageSize: 5,
                total: 100
            });



            /**
             * 搜索函数
             */
            const routes = ref([]);
            const onSearch = () => {
                axios.get("/route/list",{
                    params:{
                        pageNum:searchRoute.value.pageNum,
                        pageSize:searchRoute.value.pageSize,
                        rname:searchRoute.value.rname,
                        routeintroduce:searchRoute.value.routeintroduce,
                        cid:searchRoute.value.cid,
                        sid:searchRoute.value.sid
                    }
                }).then(resp=> {
                    console.log("传入的cid为:"+searchRoute.value.cid);
                    console.log("传入的sid为:"+searchRoute.value.sid);
                    const data = resp.data;
                    if (data.success) {
                        console.log("查询数据成功");
                        console.log("查询出的数据条数为:"+data.content.list.length);
                        routes.value=data.content.list;
                        console.log("传来的total值为:"+data.content.total);
                        // 修改分页信息中的 total 信息
                        pagination.value.total=data.content.total;
                    } else {
                        message.error(data.message);
                    }
                })
            }


            /**
             * 获取所有的分类
             */
            const categorys = ref([]);
            const getCategorys = () => {
                axios.get("/category/getAll").then(resp=> {
                    const data = resp.data;
                    if (data.success) {
                        console.log("查询数据成功");
                        console.log("查询出的数据条数为:"+data.content.length);
                        categorys.value=data.content;
                    } else {
                        message.error(data.message);
                    }
                })
            }

            /**
             * 获取所有的旅行社
             */
            const sellers = ref([]);
            const getSellers = () => {
                axios.get("/seller/getAll").then(resp=> {
                    const data = resp.data;
                    if (data.success) {
                        console.log("查询数据成功");
                        console.log("查询出的数据条数为:"+data.content.length);
                        sellers.value=data.content;
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
                getCategorys();
                getSellers();
            });

            return {
                loading,
                searchRoute,
                pagination,
                reset,
                routes,
                columns,
                onSearch,
                categorys,
                sellers,
                getSellers,
                getCategorys
            };
        },
    }
</script>

<style scoped>

</style>