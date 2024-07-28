import {createRouter,createWebHistory}from "vue-router"
import RegisterForm from '@/views/RegisterForm.vue';
import LoginForm from '@/views/LoginForm.vue';
import Fashion from "@/views/fashion.vue"
import PChangePassword from "@/views/PChangePassword.vue";
import UserInfo from "@/views/UserInfo.vue";
import fashion from "@/views/fashion.vue"
import pizza from "@/order/pizza.vue"
import steak from "@/order/steak.vue"
import rice from "@/order/rice.vue"
import dessert from "@/order/dessert.vue"
import sweet from "@/order/sweet.vue"
import drink from "@/order/drink.vue"
import soup from "@/order/soup.vue"
import single from "@/order/single.vue"
import discount from "@/order/discount.vue"
import shopping from "@/views/checkout.vue";
import welcome from "@/views/welcome.vue";
import paccount from "@/views/Paccount.vue";
import pmainpage from "@/views/Pmainpage.vue";
import PAddAddress from "@/views/PAddAddress.vue";
import PAddressList from "@/views/PAddressList.vue";
import cart from "@/views/cart.vue";
import sending from "@/views/sending.vue";

    const routes = [
        { path: '/register', component: RegisterForm },
        { path: '/login', component: LoginForm },
        { path: '/fashion', component: Fashion},
        { path: '/changepassword', component:PChangePassword},
        { path: '/userinfo', component: UserInfo},
        { path: '/fashion', component: fashion },
        { path: '/pizza', component: pizza },
        { path: '/steak', component: steak },
        { path: '/rice', component: rice },
        { path: '/dessert', component: dessert },
        { path: '/sweet', component: sweet },
        { path: '/drink', component: drink },
        { path: '/soup', component: soup },
        { path: '/single', component: single },
        { path: '/discount', component: discount },
        { path: '/shopping', component: shopping },
        { path: '/welcome', component: welcome },
        { path: '/paccount', component: paccount },
        { path: '/pmainpage', component: pmainpage },
        {path: '/PAddAddress', component: PAddAddress},
        {path: '/PAddressList', component: PAddressList},
        {path: '/cart', component: cart},
        {path: '/sending', component: sending},
        { path: '/', redirect: '/login' }
    ];

    const router = createRouter({
        history: createWebHistory(import.meta.env.BASE_URL),
        routes

});
export default router;
