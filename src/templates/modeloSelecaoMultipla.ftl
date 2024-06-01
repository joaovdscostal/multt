<div class="card">
              <div class="card-header">

                <!-- Title -->
                <h4 class="card-header-title">
                 ${nome}
                </h4>

                <!-- Dropdown -->
                <div class="dropdown">
                  <button class="btn btn-sm btn-primary addDinamico" type="button">
                    Adicionar
                  </button>

                </div>

              </div>
              <div class="card-body">

                <!-- List group -->
                <div class="list-group list-group-flush my-n3">


                  <#if (dados?if_exists?size == 0)>
						N&atilde;o existem itens adicionados</td>

						<#else>
						 <#list dados as dado>


						<div class="list-group-item item-dinamico-${dado.util1?c}" >
                    <div class="row align-items-center">
                      <div class="col ml-n2">

                        <!-- Title -->
                        <h4 class="mb-1">
                         <#list dado.util2 as imp>
								${imp} &nbsp;
							</#list>
                        </h4>
                        <input type="hidden" name="${objetoInput}" value="${dado.util1?c}"/>


                      </div>
                      <div class="col-auto">

                        <!-- Dropdown -->
                        <div class="dropdown">
                          <a class="dropdown-ellipses dropdown-toggle" href="javascript:void(0)" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fe fe-more-vertical"></i>
                          </a>
                          <div class="dropdown-menu dropdown-menu-right">
                            <a class="dropdown-item remover-item" data-id="${dado.util1?c}" href="javascript:void(0)" >
                              Remover
                            </a>
                          </div>
                        </div>

                      </div>
                    </div> <!-- / .row -->
                  </div>

						</#list>
						</#if>
                </div>

              </div>
            </div>

