/**
 * Created by Administrator on 2017/4/11.
 */

// define template
const Foo = { template: '<div>foo</div>'}
const Bar = { template: '<div>bar</div>'}

// define routes
const routes = [
    { path: '/foo', component: Foo},
    { path: '/bar', component: Bar}
]

// create router instance
const router = new VueRouter({
    routes: routes
})

// monut router
const app = new Vue({
    router
}).$mount('#app')

