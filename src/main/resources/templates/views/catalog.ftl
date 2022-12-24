<#import "../templates.ftl" as templates>

<@templates.base>
    <div class="slider">
        <div class="slider__wrapper">
            <div class="slider__item">
                <div><img src="assets/lessgo.png"></div>
            </div>
            <div class="slider__item">
                <div>Новые поступления!</div>
            </div>
        </div>
        <a class="slider__control slider__control_left" href="#" role="button"></a>
        <a class="slider__control slider__control_right slider__control_show" href="#" role="button"></a>
    </div>
    <div id="app-main">
        <h2 id="pizza">Пицца</h2>
        <div class="card-container">
            <#list pizza as product>
                <div class="card">
                    <img src="${product.img}" width="292" height="292"/>
                    <h2>${product.name}</h2>
                    <p>${product.description}</p>
                    <a href="#" class="bn" @click.prevent="show(${product.id})">Заказать</a>
                </div>
            </#list>
        </div>

        <h2 id="deserts">Десерты</h2>
        <div class="card-container">
            <#list deserts as product>
                <div class="card">
                    <img src="${product.img}" width="292" height="292"/>
                    <h2>${product.name}</h2>
                    <p>${product.description}</p>
                    <a href="#" class="bn" @click.prevent="show(${product.id})">Заказать</a>
                </div>
            </#list>
        </div>

        <h2 id="snacks">Закуски</h2>
        <div class="card-container">
            <#list snacks as product>
                <div class="card">
                    <img src="${product.img}" width="292" height="292"/>
                    <h2>${product.name}</h2>
                    <p>${product.description}</p>
                    <a href="#" class="bn" @click.prevent="show(${product.id})">Заказать</a>
                </div>
            </#list>
        </div>

        <h2 id="drinks">Напитки</h2>
        <div class="card-container">
            <#list drinks as product>
                <div class="card">
                    <img src="${product.img}" width="292" height="292"/>
                    <h2>${product.name}</h2>
                    <p>${product.description}</p>
                    <a href="#" class="bn" @click.prevent="show(${product.id})">Заказать</a>
                </div>
            </#list>
        </div>

        <div id="darkbg" v-if="product">
            <div id="window">
                <a href="#" class="close" @click.prevent="close">x</a>
                <div class="wcard" style="vertical-align: top">
                    <h2>{{product.name}}</h2>
                    <p>{{product.description}}</p>
                </div>
                <img :src="product.img" class="cardimg">
                <a id="cost" href="#" @click.prevent="toBasket()">Заказать за {{product.cost}}р</a>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script>
        var slider = multiItemSlider('.slider');
        new Vue({
            el: '#app-main',
            data: {product: null},
            methods: {
                show(id) {
                    axios.get('/product/' + id)
                        .then(response => this.product = response.data)
                        .catch((error) => console.error(error));
                    // .then(() => console.log("finale"));
                },
                toBasket() {
                    axios.get('/basket/' + this.product.id).catch((error) => console.error(error));
                    this.close();
                },
                close() {
                    this.product = null;
                }
            }
        });
    </script>
</@templates.base>