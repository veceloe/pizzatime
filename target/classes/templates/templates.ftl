<#macro base>
    <!doctype html>
    <html lang="en">
        <#include "parts/head.ftl">
        <body>
            <div>
                <header><#include "parts/header.ftl"></header>
                <article><#nested/></article>
            </div>
            <#include "parts/footer.ftl">
        </body>
    </html>
</#macro>