$(function() {
	// solving the action menu problem
	switch (menu) {
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case "All Products":
		$('#listProducts').addClass('active');
		break;
	default:
		$('#listProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}

	// code for jquery for table
	/*var products =[
		['1', 'ABC'],
		['2', 'DEF'],
		['3', 'IJK'],
		['4', 'NMP'],
		['5', 'SRQ'],
		['6', 'TUV'],
		['7', 'LOP'],
		['8', 'WTB'],
		['9', 'XYZ']
	];*/

	var $table = $('#productListTable');

	// execute the below code only where we have this table
	if($table.length){
		// console.log('Inside the table!');

		var jsonUrl = '';
		if(window.categoryId == ''){
			jsonUrl = window.contextRoot + '/json/data/all/products';
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/' + window.categoryId + '/products';
		}

		$table.DataTable({
			lengthMenu: [[3,5,10,-1], ['3', '5', '10', 'all']],
			pageLength: 5,
			/*data: products*/
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},
			columns:[
				{
					data: 'code',
					mRender: function(data, type, row){
						return '<img src="' + window.contextRoot + '/resources/images/' + data + '.jpg" class="dataTableImg"/>';
					}
				},
				{
					data: 'name'
				},
				{
					data: 'brand'
				},
				{
					data: 'unitPrice',
					mRender: function(data, type, row){
						return '&#8377;' + data
					}
				},
				{
					data: 'quantity',
					mRender: function(data, type, row){
						if(data < 1){
							return '<span style="color:red">Out of Stock!</span>';
						}
						return data;
					}
				},
				{
					data: 'id',
					bSortable: false,
					mRender: function(data, type, row){
						var str = '';
						str += '<a href="' + window.contextRoot + '/show/' + data + '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';
						
						if(row.quantity < 1){
							str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						} else {
							str += '<a href="' + window.contextRoot + '/cart/add/' + data + '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						}
						
						return str;
					}
				}
			]
		});
	}

});


