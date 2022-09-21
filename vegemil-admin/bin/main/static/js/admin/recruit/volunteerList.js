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

function copyNotice(sTh){
	if(confirm('복사하시겠습니까?')){
		$.ajax({
			url : '/admin/recruit/noticeCopy?sTh=' + sTh,
			type : "get",
			dataType : "json",
			success : function(data) {
				if(data == "false"){
					alert("채용공고 복사에 실패했습니다.");
				}
				else{
					alert("채용공고 복사에 성공했습니다.");
					window.location.reload();
					$('.datatables-basic').DataTable().ajax.reload();
				}
				
			},
			error : function(){
			}
		});
	}
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



function creatTable(sTh,joinOk,company,close){
	var dt_basic_table = $('.volunteerList'),
    dt_date_table = $('.dt-date');

  // DataTable with buttons
  // --------------------------------------------------------------------

  if (dt_basic_table.length) {
    var dt_basic = dt_basic_table.DataTable({
      ajax: '/admin/recruit/volunteerList/table?sTh=' + sTh,
      columns: [
      	{ data: 'idx' },
        { data: 'memName' },
        { data: 'joinOk' },
        { data: 'birthday' },
        { data: 'sex' },
        { data: 'joinCompany' },
        { data: 'joinField1' },
        { data: 'joinArea1' },
        { data: 'schName4' },
        { data: 'schMajor4' },
        { data: 'idx' },
        { data: 'rPortFile' },
        { data: 'joinDate' },
      ],
      columnDefs: [
      	{
      		targets: 0,
      		className: "align-center",
      		orderable: false,
          	render: function (data, type, full, meta) {
	            return (
	              '<div class="form-check">' +
		              '<input type="checkbox" class="form-check-input" id="customCheck2" name="checkList"/>' +
		              '<label class="form-check-label" for="customCheck2"></label>' +
	              '</div>'
	            );
	        }
      	},
      	{
      		targets: 1,
      		className: "text-center",
      		orderable: false
      	},
      	{
      		targets: 2,
      		className: "text-center",
      		orderable: false,
          	render: function (data, type, full, meta) {
          		var checked1 = '';
          		var checked2 = '';
          		var checked3 = '';
          		var checked4 = '';
          		
          		if(full['joinOk'] == '접수중')	checked1 = 'checked';
          		if(full['joinOk'] == '1')		checked2 = 'checked';
          		if(full['joinOk'] == '2')		checked3 = 'checked';
          		if(full['joinOk'] == '3')		checked4 = 'checked';
          		
	            return (
	              '<div class="form-check form-check-inline">' +
		              '<input class="form-check-input" type="radio" name="radioName' + full['idx'] + '" id="radioId01' + full['idx'] + '" value="접수증" ' + checked1 + ' />' +
		              '<label class="form-check-label" for="radioId01' + full['idx'] + '">서류</label>' +
	              '</div>' + 
	              '<div class="form-check form-check-inline">' +
		              '<input class="form-check-input" type="radio" name="radioName' + full['idx'] + '" id="radioId02' + full['idx'] + '" value="1" ' + checked2 + '/>' +
		              '<label class="form-check-label" for="radioId02' + full['idx'] + '">1차</label>' +
	              '</div>' +
	              '<div class="form-check form-check-inline">' +
		              '<input class="form-check-input" type="radio" name="radioName' + full['idx'] + '" id="radioId03' + full['idx'] + '" value="2" ' + checked3 + '/>' +
		              '<label class="form-check-label" for="radioId03' + full['idx'] + '">2차</label>' +
	              '</div>' + 
	              '<div class="form-check form-check-inline">' +
		              '<input class="form-check-input" type="radio" name="radioName' + full['idx'] + '" id="radioId04' + full['idx'] + '" value="3" ' + checked4 + '/>' +
		              '<label class="form-check-label" for="radioId04' + full['idx'] + '">3차</label>' +
	              '</div>'
	            );
	        }
      	},
      	{
      		targets: 3,
      		orderable: false
      	},
      	{
      		targets: 4,
      		orderable: false,
          	render: function (data, type, full, meta) {
          		var sex;
          		
          		if(full['sex'] == '1') sex='남'
          		else if(full['sex'] == '2') sex='여'
          			
	            return (sex);
	        }
      	},
      	{
      		targets: 5,
      		orderable: false
      	},
      	{
      		targets: 6,
      		orderable: false
      	},
      	{
      		targets: 7,
      		orderable: false
      	},
      	{
      		targets: 8,
      		orderable: false,
          	render: function (data, type, full, meta) {
          		if(full['schName4'] == null) return '';
	        }
      	},
      	{
      		targets: 9,
      		orderable: false,
          	render: function (data, type, full, meta) {
          		if(full['schMajor4'] == null) return '';
	        }
      	},
      	{
      		targets: 10,
      		orderable: false,
          	render: function (data, type, full, meta) {
	            return (
	            	'<a href="./01_recruit-detail.html" target="_blank">' + 
	            		'<button type="button" class="btn btn-outline-primary btn-sm">보기</button>' +
	            	'</a>'
	            );
	        }
      	},
      	{
      		targets: 11,
      		orderable: false,
          	render: function (data, type, full, meta) {
          		var tag='';
          		
          		if(full['rPortFile'] == null || full['rPortFile'] == '') tag='-';
          		else tag='<button type="button" class="btn btn-outline-primary btn-sm">다운로드</button>';
          			
	            return (tag);
	        }
      	},
      	{
      		targets: 12,
      		orderable: false,
          	render: function (data, type, full, meta) {
				return(full['joinDate'].substr(0,10));
	        }
      	}
      ],
      order: [[0, 'desc']],
      dom:
        '<"card-header border-bottom p-1"><"d-flex justify-content-between align-items-center mx-0 row"<"col-sm-12 col-md-6"l><"col-sm-12 col-md-6"f>>t<"d-flex justify-content-between mx-0 row"<"col-sm-12 col-md-6"p>>',
      displayLength: 7,
      lengthMenu: [7, 10, 25, 50, 75, 100],
      language: {
      	search : '검색',
        paginate: {
          previous: '',
          next: ''
        }
      },
      info: false
    });
    $('input.dt-input').on('keyup', function () {
	    filterColumn($(this).val());
	  });
  }
}
