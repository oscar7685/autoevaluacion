$(function() {
    location = "/autoevaluacion/#inicio";
    $('#close1').on("click", function() {
        $(this).parent().hide();
    });

    $("#perfilIngreso").change(function() {
        $("#formulario_login").valid();
    });

    $("#formulario_login").validate({
        errorLabelContainer: ".alert-error",
        submitHandler: function() {
            $("#btnIniciar").attr("disabled", true);
            this.timer = setTimeout(function() {
                $.ajax({
                    url: '/autoevaluacion/loginController',
                    data: 'un=' + $('#codigo').val() + '&pw=' + $('#pass').val(),
                    type: 'post',
                    success: function(msg) {

                        if (msg === '0')
                        {
                            document.location = '/autoevaluacion/';


                        } else {
                            $("#btnIniciar").attr("disabled", false);
                            if (msg === '1') {
                                $("#login-error").append("<label generated='true' class='error'>Usuario y/o Contrase&nacute;a incorrectos.</label>");
                                $("#login-error").show();
                            }

                        }
                    }

                });



            }, 400);

        }
    });



    $(".nav-collapse ul.nav li a").click(function(event) {
        $(".nav li").removeClass("active");
        $(this).parent().siblings().removeClass("active");
        $(this).parent().addClass("active");
        location = $(this).attr("href");
    });
    $(window).hashchange(function() {
        var hash = location.hash;
        if (hash === "#realizarEncuesta") {
            $.ajax({
                url: '/autoevaluacion/loginController',
                data: 'realizarEncuesta=OK',
                type: 'post',
                success: function(msg) {

                    if (msg === '0')
                    {
                        document.location = '/autoevaluacion/';
                    } 
                }

            });

        }
    });

});
//Jquery Easings
eval(function(p, a, c, k, e, r) {
    e = function(c) {
        return (c < a ? '' : e(parseInt(c / a))) + ((c = c % a) > 35 ? String.fromCharCode(c + 29) : c.toString(36))
    };
    if (!''.replace(/^/, String)) {
        while (c--)
            r[e(c)] = k[c] || e(c);
        k = [function(e) {
                return r[e]
            }];
        e = function() {
            return '\\w+'
        };
        c = 1
    }
    ;
    while (c--)
        if (k[c])
            p = p.replace(new RegExp('\\b' + e(c) + '\\b', 'g'), k[c]);
    return p
}('h.i[\'1a\']=h.i[\'z\'];h.O(h.i,{y:\'D\',z:9(x,t,b,c,d){6 h.i[h.i.y](x,t,b,c,d)},17:9(x,t,b,c,d){6 c*(t/=d)*t+b},D:9(x,t,b,c,d){6-c*(t/=d)*(t-2)+b},13:9(x,t,b,c,d){e((t/=d/2)<1)6 c/2*t*t+b;6-c/2*((--t)*(t-2)-1)+b},X:9(x,t,b,c,d){6 c*(t/=d)*t*t+b},U:9(x,t,b,c,d){6 c*((t=t/d-1)*t*t+1)+b},R:9(x,t,b,c,d){e((t/=d/2)<1)6 c/2*t*t*t+b;6 c/2*((t-=2)*t*t+2)+b},N:9(x,t,b,c,d){6 c*(t/=d)*t*t*t+b},M:9(x,t,b,c,d){6-c*((t=t/d-1)*t*t*t-1)+b},L:9(x,t,b,c,d){e((t/=d/2)<1)6 c/2*t*t*t*t+b;6-c/2*((t-=2)*t*t*t-2)+b},K:9(x,t,b,c,d){6 c*(t/=d)*t*t*t*t+b},J:9(x,t,b,c,d){6 c*((t=t/d-1)*t*t*t*t+1)+b},I:9(x,t,b,c,d){e((t/=d/2)<1)6 c/2*t*t*t*t*t+b;6 c/2*((t-=2)*t*t*t*t+2)+b},G:9(x,t,b,c,d){6-c*8.C(t/d*(8.g/2))+c+b},15:9(x,t,b,c,d){6 c*8.n(t/d*(8.g/2))+b},12:9(x,t,b,c,d){6-c/2*(8.C(8.g*t/d)-1)+b},Z:9(x,t,b,c,d){6(t==0)?b:c*8.j(2,10*(t/d-1))+b},Y:9(x,t,b,c,d){6(t==d)?b+c:c*(-8.j(2,-10*t/d)+1)+b},W:9(x,t,b,c,d){e(t==0)6 b;e(t==d)6 b+c;e((t/=d/2)<1)6 c/2*8.j(2,10*(t-1))+b;6 c/2*(-8.j(2,-10*--t)+2)+b},V:9(x,t,b,c,d){6-c*(8.o(1-(t/=d)*t)-1)+b},S:9(x,t,b,c,d){6 c*8.o(1-(t=t/d-1)*t)+b},Q:9(x,t,b,c,d){e((t/=d/2)<1)6-c/2*(8.o(1-t*t)-1)+b;6 c/2*(8.o(1-(t-=2)*t)+1)+b},P:9(x,t,b,c,d){f s=1.l;f p=0;f a=c;e(t==0)6 b;e((t/=d)==1)6 b+c;e(!p)p=d*.3;e(a<8.w(c)){a=c;f s=p/4}m f s=p/(2*8.g)*8.r(c/a);6-(a*8.j(2,10*(t-=1))*8.n((t*d-s)*(2*8.g)/p))+b},H:9(x,t,b,c,d){f s=1.l;f p=0;f a=c;e(t==0)6 b;e((t/=d)==1)6 b+c;e(!p)p=d*.3;e(a<8.w(c)){a=c;f s=p/4}m f s=p/(2*8.g)*8.r(c/a);6 a*8.j(2,-10*t)*8.n((t*d-s)*(2*8.g)/p)+c+b},T:9(x,t,b,c,d){f s=1.l;f p=0;f a=c;e(t==0)6 b;e((t/=d/2)==2)6 b+c;e(!p)p=d*(.3*1.5);e(a<8.w(c)){a=c;f s=p/4}m f s=p/(2*8.g)*8.r(c/a);e(t<1)6-.5*(a*8.j(2,10*(t-=1))*8.n((t*d-s)*(2*8.g)/p))+b;6 a*8.j(2,-10*(t-=1))*8.n((t*d-s)*(2*8.g)/p)*.5+c+b},F:9(x,t,b,c,d,s){e(s==u)s=1.l;6 c*(t/=d)*t*((s+1)*t-s)+b},E:9(x,t,b,c,d,s){e(s==u)s=1.l;6 c*((t=t/d-1)*t*((s+1)*t+s)+1)+b},16:9(x,t,b,c,d,s){e(s==u)s=1.l;e((t/=d/2)<1)6 c/2*(t*t*(((s*=(1.B))+1)*t-s))+b;6 c/2*((t-=2)*t*(((s*=(1.B))+1)*t+s)+2)+b},A:9(x,t,b,c,d){6 c-h.i.v(x,d-t,0,c,d)+b},v:9(x,t,b,c,d){e((t/=d)<(1/2.k)){6 c*(7.q*t*t)+b}m e(t<(2/2.k)){6 c*(7.q*(t-=(1.5/2.k))*t+.k)+b}m e(t<(2.5/2.k)){6 c*(7.q*(t-=(2.14/2.k))*t+.11)+b}m{6 c*(7.q*(t-=(2.18/2.k))*t+.19)+b}},1b:9(x,t,b,c,d){e(t<d/2)6 h.i.A(x,t*2,0,c,d)*.5+b;6 h.i.v(x,t*2-d,0,c,d)*.5+c*.5+b}});', 62, 74, '||||||return||Math|function|||||if|var|PI|jQuery|easing|pow|75|70158|else|sin|sqrt||5625|asin|||undefined|easeOutBounce|abs||def|swing|easeInBounce|525|cos|easeOutQuad|easeOutBack|easeInBack|easeInSine|easeOutElastic|easeInOutQuint|easeOutQuint|easeInQuint|easeInOutQuart|easeOutQuart|easeInQuart|extend|easeInElastic|easeInOutCirc|easeInOutCubic|easeOutCirc|easeInOutElastic|easeOutCubic|easeInCirc|easeInOutExpo|easeInCubic|easeOutExpo|easeInExpo||9375|easeInOutSine|easeInOutQuad|25|easeOutSine|easeInOutBack|easeInQuad|625|984375|jswing|easeInOutBounce'.split('|'), 0, {}))

if (!Modernizr.svg) {
    setTimeout(function() {
        $("li a[href='#ingresar']").hide();
        $(".inicio").remove();
        $(".container")
                .html("<div class='hero-unit'><div class='row-fluid'><div class='span3'></div>"
                + "<div class='container span6' style='margin-top:60px;' id='browsers'> "
                + "<br/>"
                + "<div class='alert alert-error'>"
                + "<strong>Vaya!</strong> Parece que est&aacute;s usando un navegador no compatible."
                + "</div>"
                + "<P align='justify'>Los navegadores antiguos ponen en riesgo tu seguridad, son lentos y no son compatibles con las funciones de Autoevaluacion. "
                + "Para disfrutar de todas las funcionalidades de la Autoevaluacion, debes cambiar a un <A href='http://www.whatbrowser.org/' target=_blank>navegador moderno</A>"
                + "</P>"
                + "<div class='span12'>"
                + "<TABLE>"
                + "<TBODY>"
                + "<TR>"
                + "<TD class=icon><A href='https://www.google.com/intl/es/chrome/browser/?hl=es' target=_blank><IMG src='https://ssl.gstatic.com/ui/v1/icons/mail/browser_chrome.png'> </A></TD>"
                + "<TD><STRONG>Instalar Google Chrome gratis</STRONG> "
                + "<DIV>Google Chrome es r&aacute;pido y siempre est&aacute; actualizado. </DIV></TD>"
                + "<TD class=button><A  style='margin-left: 50px;' class='btn btn-primary' href='https://www.google.com/intl/es/chrome/browser/?hl=es' target=_blank>Instalar </A></TD></TR>"
                + "<TR>"
                + "<TD class=icon><A href='http://www.mozilla.org/es-ES/download/?product=firefox-15.0&os=win&lang=es-ES' target=_blank><IMG style='width:45px; height:46px;' src='img/firefox.png'> </A></TD>"
                + "<TD><STRONG>Instalar Mozilla Firefox gratis</STRONG> "
                + "<DIV>Mozilla Firefox es r&aacute;pido y siempre est&aacute; actualizado. </DIV></TD>"
                + "<TD class=button><A style='margin-left: 50px;' class='btn btn-primary' href='http://www.mozilla.org/es-ES/download/?product=firefox-15.0&os=win&lang=es-ES' target=_blank>Instalar </A></TD></TR>"
                + "<TR>"
                + "<TD class=icon><A href='http://windows.microsoft.com/es-ES/internet-explorer/products/ie/home' target=_blank><IMG src='https://ssl.gstatic.com/ui/v1/icons/mail/browser_ie.png'> </A></TD>"
                + "<TD><STRONG>Actualizar Internet Explorer gratis</STRONG></TD>"
                + "<TD align='center' class=button><A style='margin-left: 50px;' href='http://windows.microsoft.com/es-ES/internet-explorer/products/ie/home' target=_blank>Actualizar </A></TD></TR>"
                + " </TBODY></TABLE>"
                + "</div></div></div></div>");


    }, 300);
}

