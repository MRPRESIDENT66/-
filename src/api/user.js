import axios from 'axios';


const request = axios.create({
    baseURL: '/api',
    timeout: 5000,
});

request.interceptors.request.use(
    config => {
        const token = localStorage.getItem('token'); // 从 localStorage 获取令牌
        if (token) {
            //config.headers['Authorization'] = `Bearer ${token}`;
            config.headers.Authorization = token;
        }
        return config;
    },
    error => {
            return  Promise.reject(error);
    }
);

export const userRegisterService = (registerData) => {
    const params = new URLSearchParams();
    for (let key in registerData) {
        params.append(key, registerData[key]);
    }
    return request.post('user/register', params);
};

export const userLoginService = (loginData) => {
    const params = new URLSearchParams();
    for (let key in loginData) {
        params.append(key, loginData[key]);
    }
    return request.post('user/login', params)
        .then(response => {
            if (response && response.data) {
                console.log(response.data.data);
                const token = response.data.data; // 假设令牌在 response.data.data 中

                if (token) {
                    localStorage.setItem('token', token); // 存储令牌到 localStorage
                    console.log(localStorage.getItem('token'));
                } else {
                    throw new Error('Token is missing in the response');
                }
                return response;
            } else {
                throw new Error('Invalid response format');
            }
        })
        .catch(error => {
            console.error('Login failed:', error);
            throw error; // 抛出错误以便调用者处理
        });
};

export const addcart = (product) => {
    return request.patch('order/count', { product }, {

    });
}

export const changePassword = (oldPassword, newPassword, rePassword) => {
    // 创建一个包含密码的 JSON 对象
    const data = {
        old_pwd: oldPassword,
        new_pwd: newPassword,
        re_pwd: rePassword
    };
    return request.patch('user/updatePwd', data);
};

// export const fetchUserInfo = () => {
//     return request.get('/user/userinfo')
//         .then(response => response.data)
//         .catch(error => {
//             console.error('Error fetching user info:', error);
//             throw error; // 抛出错误以便在调用方处理
//         });
// };

export const getUserInfo = () => {
    return request.get('/user/userinfo');
}

export const updateuser = (updateData) => {

    return request.put('user/update', updateData);
};


export const checkinfo = ()=>{
    return request.get('/check/info');
}

export const totalprice = () => {
    return request.get('check/total');
}

export const deletequantity = () => {
    return request.post('check/delete');
}

export const create = ()=>{
    return request.post('check/create');
}

export const checkoutinfo =()=>{
    return request.get('check/checkoutinfo');
}


