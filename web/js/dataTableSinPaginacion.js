/* Default class modification */
$.extend($.fn.dataTableExt.oStdClasses, {
    "sWrapper": "dataTables_wrapper form-inline"
});

/* TableTools Bootstrap compatibility
 * Required TableTools 2.1+
 */
if ($.fn.DataTable.TableTools) {
    // Set the classes that TableTools uses to something suitable for Bootstrap
    $.extend(true, $.fn.DataTable.TableTools.classes, {
        "container": "DTTT btn-group",
        "buttons": {
            "normal": "btn",
            "disabled": "disabled"
        },
        "collection": {
            "container": "DTTT_dropdown dropdown-menu",
            "buttons": {
                "normal": "",
                "disabled": "disabled"
            }
        },
        "print": {
            "info": "DTTT_print_info modal"
        },
        "select": {
            "row": "active"
        }
    });
    // Have the collection use a bootstrap compatible dropdown
    $.extend(true, $.fn.DataTable.TableTools.DEFAULTS.oTags, {
        "collection": {
            "container": "ul", "button": "li",
            "liner": "a"
        }
    });
}


/* Table initialisation */
$(document).ready(function() {
    var oTable = $('#tablaX').dataTable({
        "bPaginate": false,
        "oLanguage": {
            "sProcessing": "Procesando...",
            "sLengthMenu": "Mostrar _MENU_ registros",
            "sZeroRecords": "No se encontraron resultados",
            "sEmptyTable": "Ningún dato disponible en esta tabla",
            "sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            "sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
            "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix": "",
            "sSearch": "Buscar:",
            "sUrl": "",
            "sInfoThousands": ",",
            "sLoadingRecords": "Cargando...",
            "oAria": {
                "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
                "sSortDescending": ": Activar para ordenar la columna de manera descendente"
            }


        }
    });

    var tableTools = new $.fn.dataTable.TableTools(oTable, {
        "aButtons": [
            {
                "sExtends": "copy",
                "sButtonText": "Copiar al portapapeles",
                "sCopyMessage": "Your custom message would go here."
            },
            {
                "sExtends": "xls",
                "sButtonText": "Exportar a excel"
            },
            {
                "sExtends": "pdf",
                "sButtonText": "Exportar a pdf"
            }
        ]
        , "sSwfPath": "media/swf/copy_csv_xls_pdf.swf"
    });
    $("<div id='tools' class='span10'></div>").prependTo('#tablaX_wrapper');
    $(".DTTT_button_pdf").add
    $(tableTools.fnContainer()).prependTo('#tools');
});


