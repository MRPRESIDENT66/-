<template>
  <div id="app">
    <div class="sidebar">
      <div class="logo">
        <img src="@/assets/brand.jpg" alt="logo">
      </div>
      <nav>
        <ul>
          <li><router-link to="/welcome">返回首页</router-link></li>
          <li><router-link to="/fashion">潮新品</router-link></li>
          <li><router-link to="/pizza">比萨</router-link></li>
          <li><router-link to="/steak">牛排</router-link></li>
          <li><router-link to="/rice">面饭</router-link></li>
          <li><router-link to="/dessert">小食</router-link></li>
          <li><router-link to="/sweet">甜品</router-link></li>
          <li><router-link to="/drink">饮料</router-link></li>
          <li><router-link to="/soup">汤类</router-link></li>
          <li class="active">个人悠享</li>
          <li><router-link to="/discount">套餐优惠</router-link></li>
        </ul>
      </nav>
    </div>
    <div class="main-content">
      <header class="header">
        <div class="header-content">
          <span>晚间营业至24:00啦！</span>
        </div>
      </header>
      <section class="products">
        <div class="product-category">
          <h2>个人悠享</h2>
          <div class="product-list">
            <div class="product-card" v-for="(product, index) in products" :key="index">
              <img :src="product.image" :alt="product.name">
              <div class="product-info">
                <h3>{{ product.name }}</h3>
                <p>{{ product.description }}</p >
                <p>¥ {{ product.price }}</p >
                <div class="quantity-control">
                  <button @click="updateQuantity(product.id, -1)">-</button>
                  <span>{{ product.quantity }}</span>
                  <button @click="updateQuantity(product.id, 1)">+</button>
                </div>
              </div>
            </div>
          </div>
          <div v-if="message" class="message">{{ message }}</div>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
import { addcart } from "@/api/user.js";
export default {
name: 'App',
data() {
  return {
    products: [
      {
        id: 25,
        name: "土豆丝+回锅肉",
        description: "爽脆的土豆丝搭配经典的回锅肉，两菜两味，既有土豆的清新，又有回锅肉的香辣。简单易做，是家常菜中的人气选择。",
        price: 30,
        image: new URL('@/assets/single1.webp', import.meta.url).href,
        quantity: 0,
      },
      {
        id: 26,
        name: "鸡汤+小白菜+回锅肉",
        description: "鲜美的鸡汤，新鲜的小白菜和香辣的回锅肉，三者搭配，汤品的清甜，丰富的口感和层次。一碗下肚，温暖又满足。",
        price: 20,
        image: new URL('@/assets/single2.webp', import.meta.url).href,
        quantity: 0,
      },
      {
        id: 27,
        name: "小火锅",
        description: "小火锅以其小巧便捷而受到喜爱，可以根据个人口味选择不同的汤底和食材。无论是海鲜、肉类还是蔬菜，都能在热腾腾的小火锅中找到属于自己的美味。适合一人食或小聚，享受个性化的火锅体验。",
        price: 10,
        image: new URL('@/assets/single3.jpg', import.meta.url).href,
        quantity: 0,
      }
      // 可以添加更多的商品
    ],
    message: '',
    code:''
  };
},
methods: {
  async updateQuantity(productId, change) {
    try {
      //传递参数
      const response = await addcart(productId * change);
      //alert(response.data.data);
      //在前端显示商品数量
      const product = this.products.find(product => product.id === productId);
      if (product) {
        product.quantity += change;
        if (product.quantity < 0) {
          product.quantity = 0;
        }
      }
      //根据返回值的错误码来判断输出提示信息
      this.code=response.data.code;
      if(this.code===1){
        this.message = response.data.message;
      }
      if(this.code===0) {
        this.message = response.data.data;
      }
      // 清除消息
      setTimeout(() => {
        this.message = '';
      }, 500);

    } catch (error) {
      console.error(`Error updating product ${productId}:`, error.response ? error.response.data : error.message);
    }
  }
}
};
</script>

<style scoped>
#app {
  display: flex;
  font-family: Arial, sans-serif;
}

.sidebar {
  width: 250px;
  background-color: #f7f7f7;
  padding: 20px;
  box-shadow: 2px 0 5px rgba(0,0,0,0.1);
}

.sidebar .logo {
  text-align: center;
  margin-bottom: 20px;
}

.sidebar .logo img {
  max-width: 100%;
}

.sidebar nav ul {
  list-style: none;
  padding: 0;
}

.sidebar nav ul li {
  padding: 10px 0;
  font-size: 18px;
  cursor: pointer;
}

.sidebar nav ul li.active {
  color: #ff0000;
}

.sidebar nav ul li:hover {
  color: #ff0000;
}

.main-content {
flex: 1; /* 保持flex布局的伸缩性 */
width: 100vw; /* 设置容器宽度为100% */
max-width: 1000px; /* 可以设置一个最大宽度，根据需要调整 */
background-color: #fff;
padding: 20px;
display: flex;
justify-content: center;
align-items: center;
overflow: hidden;
}

.header {
  background-color: #ffd700;
  padding: 10px;
  text-align: center;
  font-size: 20px;
  font-weight: bold;
  color: #333;
  margin-bottom: 20px;
}

.products {
  flex: 1;
}

.product-category {
  margin-bottom: 20px;
}

.product-category h2 {
  margin-bottom: 10px;
  font-size: 24px;
  border-bottom: 2px solid #ff0000;
  padding-bottom: 5px;
}

.product-list {
  display: flex;
  flex-wrap: wrap;
}

.product-card {
  width: 200px;
  border: 1px solid #ddd;
  border-radius: 10px;
  margin: 10px;
  padding: 10px;
  text-align: center;
  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
}

.product-card img {
  max-width: 100%;
  border-radius: 10px;
}

.product-info {
  margin-top: 10px;
}

.product-info h3 {
  font-size: 18px;
  margin: 10px 0;
}

.product-info p {
  font-size: 16px;
  margin: 5px 0;
}

.product-info button {
  background-color: #ff0000;
  color: #fff;
  border: none;
  padding: 5px 10px;
  cursor: pointer;
  border-radius: 5px;
}

.product-info button:hover {
  background-color: #cc0000;
}
 .quantity-control {
display: flex;
align-items: center;
justify-content: center;
gap: 10px;
}

.quantity-control button {
background-color: #ff0000;
color: #fff;
border: none;
padding: 5px 10px;
cursor: pointer;
border-radius: 5px;
}

.quantity-control button:hover {
background-color: #cc0000;
}

.quantity-control span {
font-size: 16px;
} 
</style>