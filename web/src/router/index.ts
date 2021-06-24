import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '../views/Home.vue'
import UserAdd from '../views/user/UserAdd.vue'
import UserList from '../views/user/UserList.vue'
import UserUpdate from '../views/user/UserUpdate.vue'

import SellerAdd from '../views/seller/SellerAdd.vue'
import SellerList from '../views/seller/SellerList.vue'
import SellerUpdate from '../views/seller/SellerUpdate.vue'

import CategoryAdd from '../views/category/CategoryAdd.vue'
import CategoryList from '../views/category/CategoryList.vue'
import CategoryUpdate from '../views/category/CategoryUpdate.vue'

import RouteAdd from '../views/route/RouteAdd.vue'
import RouteList from '../views/route/RouteList.vue'
import RouteUpdate from '../views/route/RouteUpdate.vue'
import RouteImage from '../views/route/RouteImage.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/user-add',
    name: 'UserAdd',
    component: UserAdd
  },
  {
    path: '/user-list',
    name: 'UserList',
    component: UserList
  },
  {
    path: '/user-update',
    name: 'UserUpdate',
    component: UserUpdate
  },
    
  {
    path: '/seller-add',
    name: 'SellerAdd',
    component: SellerAdd
  },
  {
    path: '/seller-list',
    name: 'SellerList',
    component: SellerList
  },
  {
    path: '/seller-update',
    name: 'SellerUpdate',
    component: SellerUpdate
  },

  {
    path: '/category-add',
    name: 'CategoryAdd',
    component: CategoryAdd
  },
  {
    path: '/category-list',
    name: 'CategoryList',
    component: CategoryList
  },
  {
    path: '/category-update',
    name: 'CategoryUpdate',
    component: CategoryUpdate
  },


  {
    path: '/route-add',
    name: 'RouteAdd',
    component: RouteAdd
  },
  {
    path: '/route-list',
    name: 'RouteList',
    component: RouteList
  },
  {
    path: '/route-update',
    name: 'RouteUpdate',
    component: RouteUpdate
  },
  {
    path: '/route-image',
    name: 'RouteImage',
    component: RouteImage
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
