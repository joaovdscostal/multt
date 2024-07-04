<script id="ModuloTemplate" type="text/x-jsrender">
{{for dados itemVar="~mod"}}
	<!-- Inicio Modulo Container -->
	   				<div class="accordion" id="accordionExample">
					  <div class="card">
					    <div class="card-header" id="mod-{{:ordem}}" style="background-color:#d2d6dc">
				    		<div style="width:100%" class="d-flex justify-content-between align-items-center">
				          		<h3><i class="fas fa-bars mr-2"></i><input type="checkbox" class="mr-2"/> {{:nome}} </h3>					          		
				          	</div>
					      	<h2 class="mb-0">
					      		
						        <a href="#" class="btn btn-circle btn-light mr-2" type="button" data-toggle="collapse" data-target="#modCollapse-{{:ordem}}" aria-expanded="true" aria-controls="modCollapse-{{:ordem}}">
						          	<i class="far fa-expand"></i>
						        </a>

								<a href="#" class="btn btn-circle btn-primary mr-2" type="button" data-toggle="collapse" data-target="#modCollapse-{{:ordem}}" aria-expanded="true" aria-controls="modCollapse-{{:ordem}}">
						          	<i class="fal fa-pencil-alt"></i>
						        </a>					    
						        
						        <a href="#" class="btn btn-circle btn-primary add-conteudo" data-mod-id="{{:id}}" type="button">
						          	<i class="far fa-plus"></i>
						        </a>
					      	</h2>
					    </div>
					
					    <div id="modCollapse-{{:ordem}}" class="collapse" aria-labelledby="mod-{{:ordem}}" data-parent="#modCollapse-{{:ordem}}">
					      <div class="card-body">
					      	<table class="table table-responsive">
					      		<tbody>
									{{for conteudos }}
					      			<tr>     				
					      				<td style="width:100%">		      				
						      				<i class="fas fa-bars mr-2"></i> {{:titulo}} 
					      				</td>
					      				<td> <h4><span class="badge badge-success">Publicado</span></h4> </td>
					      				<td class="d-flex"> 
					      					<button class="btn btn-circle btn-secondary mr-2"> <i class="fal fa-pencil-alt"></i> </button>
					      					<button class="btn btn-circle btn-danger"> <i class="fas fa-times"></i> </button> 
					      				</td>
					      			</tr>	
					      			{{/for}}					      					      								      							      					      			
					      		</tbody>
					      	</table>
					      </div>
					    </div>
					  </div>
					  					  
					</div>
					<!-- Fim Modulo Container -->
{{/for}}
</script>