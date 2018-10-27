
var $table = $('#villeTable');
$(document).ready(function(){
	$('#villeTable').DataTable( {
		lengthMenu:[[3,5,10,-1],['3 Records','5 Records','10 Records','ALL']],
		pageLengh :5,
	    ajax: {
	        url: '/admin/villes/json',
	        dataSrc: ''
	    },
	    columns: [
	    	{data:'id'},
	    	{data:'name'},
	    	{data:'grandeInstance',
	    	 mRender:function(data,type,row){
	    		var text='<label class="switch">';
	    		if(data==true)
	    		      text+='<input name="'+row.id+'" onclick="activate(this)" type="checkbox" checked="checked">';
	    		else 
	    			  text+='<input name="'+row.id+'" onclick="activate(this)" type="checkbox">';
	    	    text+= ' <span  class="slider round"></span></label>';
	    	    return text;
	    	}},
	    	{data:'id',
	    	 mRender:function(data, type, row){
	    		 var text='<button value="'+row.name+'" id="'+data+'" onclick="editer(this)" class="btn btn-warning m-1"> <i class="fas fa-edit"></i></button>';
	    		 text+='<button id="'+data+'" class="btn btn-danger m-1" onclick="supprimer(this)"><i class="fas fa-trash"></i> </button>';
	    		 return text ;
	    	 }}
	    ]
	} );

});	

function nouvelleVille(){
	$('#modalVille').modal('show');
}

function activate(btn){
	var text ;
	if(btn.checked){
		 text='<p class="p-3 text-center">Voulez vous définir cette ville comme une ville habritant un tribunal de grande instance ?</p>';
	}else{
		text='<p class="p-3 text-center text-danger">Voulez vous retirer de la liste des villes habritant un tribunal de grande  de grande instance ?</p>';
	}
	bootbox.confirm(text,function(state){
		if(state){
			$.ajax({
				method:'POST',
				url:'/admin/ville/'+btn.name+'/activate'
				}).done(function(response){
					if(response==true){
						console.log(response)
						bootbox.alert('<p class="p-3 text-center text-success"> La modification a été enregistré avec succès ! </p>');
					}else{
						bootbox.alert('<p class="p-3 text-center text-danger"> Erreur ! La modification n\'a pas été enrégistré .</p>');
						btn.checked = !btn.checked;
					}
				});
		}else{
			btn.checked = !btn.checked;
		}
	});
}

//function to delete a city
function supprimer(btn){
	var text='<p class="p-3 text-center text-danger">Voulez vous supprimer cette ville de la base de donnée ?</p>';
	bootbox.confirm(text,function(state){
		if(state){
			window.location.href='/admin/ville/'+btn.id+'/delete';
				
		}
	});
}

function editer(btn){
	$('#vId').attr('value',btn.id);
	$('#vName').attr('value',btn.value);
	$('#modalVille').modal('show');
	
}
var $alert =$('.alert');
if($alert.length){
 setTimeout(function(){
   $alert.fadeOut('slow');
 },3000);
}