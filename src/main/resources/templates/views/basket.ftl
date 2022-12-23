<#import "../templates.ftl" as templates>

<#assign sum = 0>
<#list result as product>
    <#assign sum = sum + product.cost>
</#list>

<@templates.base>
    <if>
        <div class="mainer">
    <h1>Корзина</h1>
        <#if (sum>0)>
            <form action="/basket/send" method="POST">
                <p>Ваш телефон и email:</p>
                <input type="text" name="phone" class="phone" placeholder="+7(xxx)xxx-xx-xx"/>
                <input type="text" name="email" class="email" placeholder="Адрес электронной почты"/>
                <#list result as product>
                    <ul class="order">
                        <li><img src="${product.img}" height="100" width="100"/></li>
                        <li>${product.name}</li>
                        <li>1 шт.</li>
                        <li>${product.cost}руб.</li>
                        <li><a href="/basket/remove/${product.id}">Удалить</a></li>
                    </ul>
                </#list>
                <button class="bn">Заказать на ${sum}р</button>
            </form>
        <#else>
            <h4>Здесь ничего нету</h4>
        </#if>
    </div>
</@templates.base>
