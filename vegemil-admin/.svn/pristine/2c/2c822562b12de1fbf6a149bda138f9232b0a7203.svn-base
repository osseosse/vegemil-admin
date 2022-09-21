/**
 * DataTables Basic
 */
 
 'use strict';

// Advanced Search Functions Starts
// --------------------------------------------------------------------

// Filter column wise function
function filterColumn(val) {

    var startDate = $('.start_date').val(),
      endDate = $('.end_date').val();
      
      if (startDate !== '' && endDate !== '') {
        filterByDate(5, startDate, endDate); // We call our filter function
      }

    $('.datatables-basic').dataTable().fnDraw();

}

// Datepicker for advanced filter
var separator = ' - ',
  rangePickr = $('.flatpickr-range'),
  dateFormat = 'yyyy-MM-dd';
var options = {
  autoUpdateInput: false,
  autoApply: true,
  locale: {
    format: dateFormat,
    separator: separator
  },
  opens: $('html').attr('data-textdirection') === 'rtl' ? 'left' : 'right'
};

//
if (rangePickr.length) {
  rangePickr.flatpickr({
    mode: 'range',
    dateFormat: 'Y-m-d',
    onClose: function (selectedDates, dateStr, instance) {
      var startDate = '',
        endDate = new Date();
        
      var month_s,date_s,month_e,date_e;
      
      if((selectedDates[0].getMonth() + 1) < 10) month_s = '0'+(selectedDates[0].getMonth() + 1);
      else month_s = (selectedDates[0].getMonth() + 1);
      if(selectedDates[0].getDate() < 10) date_s = '0'+selectedDates[0].getDate();
      else date_s = selectedDates[0].getDate();
        
      if (selectedDates[0] != undefined) {
        startDate =
          selectedDates[0].getFullYear() + '-' + month_s + '-' + date_s;
        $('.start_date').val(startDate);
      }

      if((selectedDates[1].getMonth() + 1) < 10) month_e = '0'+(selectedDates[1].getMonth() + 1);
      else month_e = (selectedDates[1].getMonth() + 1);
      if(selectedDates[1].getDate() < 10) date_e = '0'+selectedDates[1].getDate();
      else date_e = selectedDates[1].getDate();
      
      if (selectedDates[1] != undefined) {
        endDate =
          selectedDates[1].getFullYear() + '-' + month_e + '-' + date_e;
        $('.end_date').val(endDate);
      }
      $(rangePickr).trigger('change').trigger('keyup');
    }
  });
}

// Advance filter function
// We pass the column location, the start date, and the end date
var filterByDate = function (column, startDate, endDate) {
  // Custom filter syntax requires pushing the new filter to the global filter array
  $.fn.dataTableExt.afnFiltering.push(function (oSettings, aData, iDataIndex) {
    var rowDate = normalizeDate(aData[column]),
      start = normalizeDate(startDate),
      end = normalizeDate(endDate);
	
    // If our date from the row is between the start and end
    if (start <= rowDate && rowDate <= end) {
      return true;
    } else if (rowDate >= start && end === '' && start !== '') {
      return true;
    } else if (rowDate <= end && start === '' && end !== '') {
      return true;
    } else {
      return false;
    }
  });
};

// converts date strings to a Date object, then normalized into a YYYYMMMDD format (ex: 20131220). Makes comparing dates easier. ex: 20131220 > 20121220
var normalizeDate = function (dateString) {
  //var date = new Date(dateString);
  var normalized = dateString.slice(0,4) + dateString.slice(5,7) + dateString.slice(8,10);
    //date.getFullYear() + '' + ('0' + (date.getMonth() + 1)).slice(-2) + '' + ('0' + date.getDate()).slice(-2);
  return normalized;
};
// Advanced Search Functions Ends

$(function () {

  var dt_basic_table = $('.datatables-basic'),
    dt_date_table = $('.dt-date');

  // DataTable with buttons
  // --------------------------------------------------------------------

  if (dt_basic_table.length) {
    var dt_basic = dt_basic_table.DataTable({
      ajax: '/admin/event/bestReview/table',
      columns: [
      	{ data: 'sIdx' },
        { data: 'sTitle' },
        { data: 'sUrl' },
        { data: 'sUid' },
        { data: 'sEdayId' },
        { data: 'sWritedate' },
        { data: '' },
        { data: 'sRank' },
      ],
      columnDefs: [
      	{
      		targets: 1,
      		orderable: false,
      		render: function (data, type, full, meta) {
      			if(full['sTitle']==null)	return '';
      			else	return full['sTitle'];
      			
      		}
      	},
      	{
      		targets: 2,
      		orderable: false,
      		render: function (data, type, full, meta) {
      			if(full['sUrl']==null)	return '';
      			else	return full['sUrl'];
      			
      		}
      	},
      	{
      		targets: 3,
      		orderable: false,
      		render: function (data, type, full, meta) {
      			if(full['sUid']==null)	return '';
      			else	return full['sUid'];
      			
      		}
      	},
      	{
      		targets: 4,
      		orderable: false,
      		render: function (data, type, full, meta) {
      			if(full['sEdayId']==null)	return '';
      			else	return full['sEdayId'];
      			
      		}
      	},
      	{
          targets: 6,
          orderable: false,
          render: function (data, type, full, meta) {
            return (
              '<div class="d-inline-flex">' +
              '<a href="#modal1" class="dropdown-item" data-toggle="modal">' +
              feather.icons['file'].toSvg({ class: 'font-small-4 mr-50' }) + '</a>' +
              
              '<div class="modal fade text-start" id="modal1" tabindex="-1" aria-labelledby="myModalLabel17" aria-hidden="true">' +
              '<div class="modal-dialog modal-dialog-centered modal-lg">' +
              '<div class="modal-content">' +
              '<div class="modal-header">' +
              '<h4 class="modal-title" id="myModalLabel17">이미지</h4>' +
              '<button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>' +
              '</div>' +
              '<div class="modal-body">' +
              '<img src="' + full['sImage'] + '">' +
              '</div>' +
              '<div class="modal-footer">' +
              '<button type="button" class="btn btn-primary" data-dismiss="modal">닫기</button>' +
              '</div>' +
              '</div>' +
              '</div>' +
              '</div>'
            );
          }
        },
        {
      		targets: 7,
      		render: function (data, type, full, meta) {
      			var rank;
      			
      			if(full['cRank']==null || full['sRank']=='')	rank = '후 보';
      			else	rank = full['sRank'] + ' 등';
      			
      			return(
					rank +
					'<div class="d-inline-flex">' +
					'<a class="pr-1 dropdown-toggle hide-arrow text-primary" data-toggle="dropdown">' +
					feather.icons['more-vertical'].toSvg({ class: 'font-small-4' }) +
					'</a>' +
					'<div class="dropdown-menu dropdown-menu-right">' +
					'<form action="/admin/event/bestReview/update?sIdx=' + full['sIdx'] + '&cRank=1" method="post" enctype="multipart/form-data">' + 
					'<input type="submit" class="btn btn-outline-primary" value="       1 등         "></input>' +
					'</form>' + 
					'<form action="/admin/event/bestReview/update?sIdx=' + full['sIdx'] + '&cRank=2" method="post" enctype="multipart/form-data">' + 
					'<input type="submit" class="btn btn-outline-primary" value="       2 등        "></input>' +
					'</form>' + 
					'<form action="/admin/event/bestReview/update?sIdx=' + full['sIdx'] + '&cRank=" method="post" enctype="multipart/form-data">' + 
					'<input type="submit" class="btn btn-outline-primary" value="      후 보        "></input>' +
					'</form>' + 
					'</div>' 
					
      			);
      		}
      	},
      ],
      order: [[0, 'desc']],
      dom:
        '<"card-header border-bottom p-1"<"head-label"><"dt-action-buttons text-right"B>><"d-flex justify-content-between align-items-center mx-0 row"<"col-sm-12 col-md-6"l><"col-sm-12 col-md-6"f>>t<"d-flex justify-content-between mx-0 row"<"col-sm-12 col-md-6"i><"col-sm-12 col-md-6"p>>',
      displayLength: 7,
      lengthMenu: [7, 10, 25, 50, 75, 100],
      buttons: [
        {
          extend: 'collection',
          className: 'btn btn-outline-secondary dropdown-toggle mr-2',
          text: feather.icons['share'].toSvg({ class: 'font-small-4 mr-50' }) + 'Export',
          buttons: [
            {
              extend: 'print',
              text: feather.icons['printer'].toSvg({ class: 'font-small-4 mr-50' }) + 'Print',
              className: 'dropdown-item',
              exportOptions: { columns: [1, 2, 3, 4, 5, 6, 7, 8, 10] }
            },
            {
              extend: 'csv',
              text: feather.icons['file-text'].toSvg({ class: 'font-small-4 mr-50' }) + 'Csv',
              className: 'dropdown-item',
              exportOptions: { columns: [1, 2, 3, 4, 5, 6, 7, 8, 10] }
            },
            {
              extend: 'excel',
              text: feather.icons['file'].toSvg({ class: 'font-small-4 mr-50' }) + 'Excel',
              className: 'dropdown-item',
              exportOptions: { columns: [1, 2, 3, 4, 5, 6, 7, 8, 10] }
            },
            {
              extend: 'pdf',
              text: feather.icons['clipboard'].toSvg({ class: 'font-small-4 mr-50' }) + 'Pdf',
              className: 'dropdown-item',
              exportOptions: { columns: [1, 2, 3, 4, 5, 6, 7, 8, 10] }
            },
            {
              extend: 'copy',
              text: feather.icons['copy'].toSvg({ class: 'font-small-4 mr-50' }) + 'Copy',
              className: 'dropdown-item',
              exportOptions: { columns: [1, 2, 3, 4, 5, 6, 7, 8, 10] }
            }
          ],
          init: function (api, node, config) {
            $(node).removeClass('btn-secondary');
            $(node).parent().removeClass('btn-group');
            setTimeout(function () {
              $(node).closest('.dt-buttons').removeClass('btn-group').addClass('d-inline-flex');
            }, 50);
          }
		}
      ],
      language: {
        paginate: {
          // remove previous & next text from pagination
          previous: '&nbsp;',
          next: '&nbsp;'
        }
      }
    });
    $('div.head-label').html('<h6 class="mb-0">베스트후기 선정</h6>');
    $('input.dt-input').on('keyup', function () {
	    filterColumn($(this).val());
	  });
  }

});
