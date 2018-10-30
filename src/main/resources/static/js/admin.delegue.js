
var $table = $('#delegueTable');
$(document).ready(function(){
	$('#delegueTable').DataTable( {
		lengthMenu:[[3,5,10,-1],['3 Records','5 Records','10 Records','ALL']],
		pageLengh :5,
	    ajax: {
	        url: '/admin/delegues/json',
	        dataSrc: ''
	    },
	    columns: [
	    	{data:'nom'},
	    	{data:'prenom'},
	    	{data:'ville',
	    	 mRender:function(data,type, row){
	    		 return data.name;
	    	 }},
	    	{data:'active',
	    	 mRender:function(data,type,row){
	    		var text='<label class="switch">';
	    		if(data==true)
	    		      text+='<input name="'+row.email+'" onclick="activate(this)" type="checkbox" checked="checked">';
	    		else 
	    			  text+='<input name="'+row.email+'" onclick="activate(this)" type="checkbox">';
	    	    text+= ' <span  class="slider round"></span></label>';
	    	    return text;
	    	}},
	    	{data:'email'}
	    ]
	} );

});	


function activate(btn){
	var text ;
	if(btn.checked){
		 text='<p class="p-3 text-center h6">Voulez vous activez ce administrateur délégué ?</p>';
		 text+='<p class="p-3 text-center h6">En activant ce utilisateur vous lui donné le droit de se connecter sur le site avec la possibilité d\'apporter des modifications.</p>';
	}else{
		text='<p class="p-3 text-center text-danger h6">Voulez vous désactiver cet utilisateur ?</p>';
	    text+='<p class="p-3 text-center text-danger h6">En désactivant cet utilisateur il n\'auras plus l\'accès au site à travers ce compte .</p>'
	}
	bootbox.confirm(text,function(state){
		if(state){
			$.ajax({
				method:'POST',
				url:'/admin/delegues/'+btn.name+'/activate'
				}).done(function(response){
					if(response==true){
						console.log(response)
						bootbox.alert('<p class="p-3 h6 text-center text-success"> La modification a été enregistré avec succès ! </p>');
					}else{
						bootbox.alert('<p class="p-3 h6 text-center text-danger"> Erreur ! La modification n\'a pas été enrégistré .</p>');
						btn.checked = !btn.checked;
					}
				});
		}else{
			btn.checked = !btn.checked;
		}
	});
}


var $alert =$('.alert');
if($alert.length){
 setTimeout(function(){
   $alert.fadeOut('slow');
 },3000);
}