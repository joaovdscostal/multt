
<div class="modal fade modalPesquisaDinamico" id="modalPesquisaDinamico"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="z-index: 20000 !important;">
  <div class="modal-dialog model-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="tituloPesquisarDinamico">Pesquisar Din&acirc;mico</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="bodyPesquisarDinamico">
      <form id="formPesquisaDinamicoExtras" onsubmit="return false;">
        </form>
        <form id="formPesquisaDinamico" onsubmit="return false;">
          <div class="form-group col-md-12">
          	<label class="control-label" for="pesquisa">Campo din&acirc;mico de pesquisa (Ex: codigo do item, nome, outros..)</label>
          	<div class="input-group">
            	<input type="text" class="form-control required" id="pesquisa" name="campoPesquisaPesquisarDinamico">
            	<div class="input-group-append">
		    		<button class="btn btn-outline-secondary " id="buttonPesquisaDinamico" type="button"><i class="fas fa-search"></i></button>
				</div>
            </div>
          </div>
        </form>

        <div id="conteudoDinamico"></div>
      </div>
    </div>
  </div>
</div>
