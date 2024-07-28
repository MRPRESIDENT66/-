<template>
    <div id="app" class="app-container">
      <div class="sidebar">
        <div class="logo">
          <img src="@/assets/brand.jpg" alt="logo">
        </div>
        <nav>
          <ul>
            <li><router-link to="/welcome">返回首页</router-link></li>
            <li><router-link to="/fashion">点餐</router-link></li>
            <li><router-link to="/discount">优惠活动</router-link></li>
            <li class="active">购物车</li>
            <li><router-link to="/sending">配送信息</router-link></li>
            <li><router-link to="/Pmainpage">我的账户</router-link></li>
          </ul>
        </nav>
        <div class="contact-info">
          <p>114514-1919810</p>
          <p>下北泽备案信息</p>
        </div>
      </div>
      <div class="main-content">
        <div class="cart">
            <div id="app">
<!--                <h1>购物车物品</h1>-->
                <div v-for="item in matchedItems" :key="item.id" class="product">
                    <el-card :body-style="{ padding: '20px' }">
                        <img :src="item.image" :alt="item.name" style="width: 100%; height: 200px; object-fit: cover;">
                        <div class="item-info">
                            <h3>{{ item.name }}</h3>
                            <p>{{ item.description }}</p>
                            <p>¥ {{ item.price }}</p>
                            <p>数量: {{ item.quantity }}</p>
                        </div>
                        <div class="quantity-control">
                            <el-button @click="updateQuantity(item.id, -1)" type="primary" style="margin: 0 10px;">-</el-button>
                            <span>{{ item.quantity }}</span>
                            <el-button @click="updateQuantity(item.id, 1)" type="primary"  style="margin: 0 10px;">+</el-button>
                        </div>
                    </el-card>
                </div>
                <div>
                    <h2></h2>
                </div>
            </div>
          <button class="checkout-btn" @click="openCheckoutModal">结算<br>总价格：¥{{ totalPrice }}</button>
          <!-- 结算窗口 -->
          <div class="checkout-modal" v-if="isCheckoutModalOpen">
            <img src="@/assets/fee.jpg" alt="Checkout" class="checkout-image">
            <button @click="closeCheckoutModal">我已付款</button>
          </div>
        </div>
      </div>
    </div>
  </template>
  
<script>
  import {reactive, onMounted, ref} from 'vue';
  import {checkinfo, deletequantity,create} from '@/api/user.js';
  import { addcart } from "@/api/user.js";
  import {totalprice} from "@/api/user.js";
  import 'element-plus/dist/index.css';
  
  export default {
    name: 'App',
    setup() {
      const orderItems = reactive([]);
      const totalPrice = ref(0);
  
      onMounted(async () => {
        try {
          const response = await checkinfo();
          const data = response.data.data;
  
  
          const response1 = await totalprice();
          totalPrice.value = response1.data.data;
  
          // console.log('Data from response:', data); // 输出解析后的数据
  
          orderItems.length = 0;
  
          data.forEach(item => {
            orderItems.push({
              id: item.dishId, // 后端返回的 dishId 对应前端的 id
              quantity: item.quantity,
            });
          });
          // console.log('OrderItems after fetch:', orderItems);  // 输出 orderItems 的内容
        } catch (error) {
          console.error('Error fetching items info:', error);
        }
      });
  
      return {
        orderItems,
        totalPrice
      };
    },
    data() {
      return {
        isCheckoutModalOpen: false,
        products: [
          {
            id: 1,
            name: "玩偶（大）",
            description: "任意消费+30元即可获取",
            price: 30,
            image: new URL('@/assets/fashion1.jpg', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 2,
            name: "玩偶（中）",
            description: "任意消费+20元即可获取",
            price: 20,
            image: new URL('@/assets/fashion2.jpg', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 3,
            name: "玩偶（小）",
            description: "任意消费+10元即可获取",
            price: 10,
            image: new URL('@/assets/fashion3.jpg', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 4,
            name: "比萨（14寸）",
            description: "中型（Medium） - 直径大约在14-16英寸，适合3-4人分享",
            price: 100,
            image: new URL('@/assets/pizza1.jpg', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 5,
            name: "比萨（10寸）",
            description: "小型（Small） - 直径大约在10-12英寸，适合2-3人分享。",
            price: 90,
            image: new URL('@/assets/pizza2.jpg', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 6,
            name: "比萨（6寸）",
            description: "个人尺寸（Personal Size） - 通常直径在6英寸左右，适合一个人食用。",
            price: 80,
            image: new URL('@/assets/pizza3.jpg', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 7,
            name: "经典西冷牛排",
            description: "精选上等西冷部位，肉质鲜嫩多汁，口感细腻。适合追求经典牛排风味的您。",
            price: 70,
            image: new URL('@/assets/steak1.jpg', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 8,
            name: "香煎肋眼牛排",
            description: "肋眼部位的牛排，油花分布均匀，煎烤后外焦里嫩，香气四溢，是肉食爱好者的不二选择。",
            price: 60,
            image: new URL('@/assets/steak2.jpg', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 9,
            name: "嫩煎菲力牛排",
            description: "菲力牛排以其肉质最为柔嫩而闻名，几乎不含脂肪，适合健康饮食者享用。",
            price: 50,
            image: new URL('@/assets/steak3.jpg', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 10,
            name: "经典意面",
            description: "采用传统意大利制作工艺，面条Q弹有嚼劲，搭配经典番茄酱或奶油蘑菇酱，每一口都是地道的地中海风情。",
            price: 20,
            image: new URL('@/assets/rice1.jpg', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 11,
            name: "芝士焗饭",
            description: "精选优质米饭，覆盖着丰富的奶酪和各种蔬菜肉类，焗烤至金黄酥脆，每一口都是满满的幸福感。",
            price: 15,
            image: new URL('@/assets/rice2.jpg', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 12,
            name: "家常拉面",
            description: "手工拉制的面条，细腻而富有弹性，搭配鲜美的骨汤和精选配料，是一碗充满家的味道的传统面食。",
            price: 10,
            image: new URL('@/assets/rice3.jpg', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 13,
            name: "小吃拼盘",
            description: "包含五个鸡翅、一张饼、两根烤肠、四个洋葱圈。",
            price: 30,
            image: new URL('@/assets/dessert1.jpg', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 14,
            name: "鱼丸",
            description: "精选新鲜鱼肉，经过精心打制，制成的鱼丸质地细腻、弹性十足。每一口都能感受到海洋的鲜美，是火锅、汤料或煮食的理想选择。",
            price: 20,
            image: new URL('@/assets/dessert2.jpg', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 15,
            name: "烤豆腐",
            description: "选用优质豆腐，经过慢火烤制，外皮金黄酥脆，内里嫩滑多汁。烤豆腐不仅保留了豆腐的原始风味，更增添了烧烤的香气，是素食者和健康饮食爱好者的美味佳肴。",
            price: 10,
            image: new URL('@/assets/dessert3.jpg', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 16,
            name: "夏日甜品",
            description: "清新的仙草搭配Q弹的芋圆，冰凉甜蜜，是夏日消暑的最佳甜品。每一口都能感受到热带风情，带来清凉一夏。",
            price: 30,
            image: new URL('@/assets/sweet1.jpg', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 17,
            name: "草莓蛋糕",
            description: "松软的蛋糕层与细腻的奶油层层叠加，顶部和夹层间点缀着新鲜草莓，酸甜可口，是庆祝生日或任何特别时刻的完美选择。",
            price: 25,
            image: new URL('@/assets/sweet2.jpg', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 18,
            name: "草莓巧克力点心",
            description: "精选优质巧克力，包裹着新鲜草莓，外层香脆，内里多汁，甜中带酸，每一口都是甜蜜与清新的完美结合。",
            price: 20,
            image: new URL('@/assets/sweet3.jpg', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 19,
            name: "酸梅汤",
            description: "传统夏日饮品，以乌梅为主料，加入甘草、冰糖等，熬煮成汤。口感酸甜适中，生津止渴，具有消暑解热的功效，是炎炎夏日里的清凉佳品。",
            price: 40,
            image: new URL('@/assets/drink1.jpg', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 20,
            name: "饮料2",
            description: "新鲜橙子榨汁，富含维生素C和天然果糖，口感清新，酸甜可口。一杯橙汁，既能补充能量，又能提供一天所需的维生素，是健康生活的优选。",
            price: 30,
            image: new URL('@/assets/drink2.jpg', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 21,
            name: "饮料3",
            description: "选用新鲜柠檬榨汁，酸爽提神，是解油腻、提神醒脑的天然饮品。加入冰块或搭配蜂蜜，即可制成一杯消暑解渴的夏日特饮。",
            price: 20,
            image: new URL('@/assets/drink3.jpg', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 22,
            name: "玉米排骨汤",
            description: "这道汤品选用新鲜排骨搭配甜玉米，经过慢火炖煮，排骨肉质鲜嫩，汤汁浓郁。玉米的甜味与排骨的鲜美完美融合，营养丰富，是家庭聚餐时的温馨选择。",
            price: 30,
            image: new URL('@/assets/soup1.jpg', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 23,
            name: "香菇枸杞汤",
            description: "精选香菇与枸杞，搭配清鸡汤底，小火慢炖，使汤品香气四溢，滋味鲜美。香菇的醇厚与枸杞的甘甜相得益彰，具有滋补养生的功效，适合追求健康生活的您。",
            price: 20,
            image: new URL('@/assets/soup2.jpg', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 24,
            name: "红枣鸡汤",
            description: "以红枣和土鸡为主料，加入姜片等佐料，经过长时间炖煮，汤色金黄，味道醇厚。红枣的甘甜与鸡肉的鲜美相结合，不仅滋味美妙，还具有补血养颜的食疗效果。",
            price: 10,
            image: new URL('@/assets/soup3.jpg', import.meta.url).href,
            quantity: 0,
          },
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
          },
          {
            id: 28,
            name: "排骨+青椒",
            description: "香嫩的排骨搭配青椒的微辣，口感丰富，营养均衡。青椒的清脆与排骨的鲜美相得益彰，是一份简单却不失风味的套餐。",
            price: 30,
            image: new URL('@/assets/discount1.webp', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 29,
            name: "土豆炖牛肉+鸡蛋羹+炒豆腐+腌萝卜",
            description: "这份套餐包含了多种口感和营养。土豆炖牛肉肉质酥烂，土豆吸满了肉汁；鸡蛋羹细腻滑嫩，是老少皆宜的佳肴；炒豆腐外焦里嫩，搭配腌萝卜的爽脆，为套餐增添了一抹清新。每一口都是满足。",
            price: 20,
            image: new URL('@/assets/discount2.jpg', import.meta.url).href,
            quantity: 0,
          },
          {
            id: 30,
            name: "麻辣烫",
            description: "麻辣烫以其丰富的自选食材和麻辣鲜香的汤底而闻名。顾客可以根据自己的喜好选择各种蔬菜、肉类、豆制品等，搭配秘制的麻辣汤底，每一口都是热辣过瘾，适合喜欢尝试不同风味的食客。",
            price: 10,
            image: new URL('@/assets/discount3.jpg', import.meta.url).href,
            quantity: 0,
          }
  
        ],
        message: '',
        code: ''
      };
    },
    computed: {
      matchedItems() {
        console.log('OrderItems:', this.orderItems);
        console.log('Products:', this.products);
  
        const matched = [];
  
        this.orderItems.forEach(orderItem => {
          this.products.forEach(product => {
            if (product.id === orderItem.id) {
              matched.push({
                ...product,
                quantity: orderItem.quantity
              });
            }
          });
        });
  
        // console.log('Matched Items:', matched); // 输出匹配到的产品列表
        return matched;
      }
    },
  
  
    methods: {
      openCheckoutModal() {
        this.isCheckoutModalOpen = true;
      },
      closeCheckoutModal() {
        this.isCheckoutModalOpen = false;
        const response= create();
        const response1 = deletequantity();
        window.location.reload();
        alert("购买成功！！！");



        //console.log(response);
        //alert(response.data.data);
      },
      async updateQuantity(productId, change) {
        try {
          //传递参数
          const response = await addcart(productId * change);
          //alert(response.data.data);
          //在前端显示商品数量
          //根据返回值的错误码来判断输出提示信息
          console.log(response.data);
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
  
          window.location.reload();
  
        } catch (error) {
          console.error(`Error updating product ${productId}:`, error.response ? error.response.data : error.message);
        }
      }
    }
  };
</script>
<style scoped>
  .app-container {
    display: flex;
    height: 100vh;
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
  
  .sidebar nav ul li:hover {
    color: #ff0000;
  }
  
  .sidebar .contact-info {
    position: absolute;
    bottom: 20px;
    font-size: 14px;
  }
  
  .main-content {
    flex: 1; /* 保持flex布局的伸缩性 */
    width: 100vw; /* 设置容器宽度为100% */
    max-width: 900px; /* 可以设置一个最大宽度，根据需要调整 */
    background-color: #fff;
    padding: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: hidden;
  }
  
  .promo-image {
  width: 100%; /* 设置图片宽度为100% */
  height: auto; /* 设置图片高度为100% */
  object-fit: fill; /* 拉伸图片以填满容器，可能会裁剪图片 */
  }
  .cart { flex: 1; padding: 20px; background-color: #f0f0f0; overflow-y: auto; /* 允许垂直滚动 */ max-height: 100vh; /* 设置最大高度为视口高度 */ }
  
  .cart-items {
  display: flex;
  flex-direction: column;
  }
  
  .cart-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 10px;
  padding: 10px;
  background-color: #ffffff;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  }
  
  .cart-item img {
  width: 50px;
  height: 50px;
  object-fit: cover;
  border-radius: 5px;
  }
  
  .item-info {
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-left: 10px;
  }
  
  .item-info h3 {
  font-size: 16px;
  color: #333333;
  }
  
  .item-info p {
  font-size: 14px;
  color: #666666;
  }
  
  button {
  background-color: #ff0000;
  color: #ffffff;
  border: none;
  padding: 5px 10px;
  margin-left: 10px;
  border-radius: 5px;
  cursor: pointer;
  }
  
  button:hover {
  background-color: #cc0000;
  }
  
  .total {
  margin-top: 20px;
  font-size: 20px;
  font-weight: bold;
  text-align: right;
  }
  
  .total-amount {
  color: #ff0000;
  }
  
  .checkout-btn {
  width: 100%;
  padding: 10px;
  margin-top: 20px;
  font-size: 18px;
    white-space: pre-wrap; /* Allows line breaks */
    display: inline-block; /* Ensures the button behaves like an inline block element */
    text-align: center; /* Centers the text */
  }
  
  .checkout-modal {
  position: fixed;
  bottom: 20px;
  right: 20px;
  width: 300px;
  background-color: #ffffff;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 20px;
  }
  
  .checkout-image {
  width: 100%;
  border-radius: 10px;
  margin-bottom: 20px;
  }
  
  .checkout-modal button {
  margin-top: 20px;
  padding: 8px 15px;
  }
</style>
  