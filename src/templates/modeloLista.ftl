<h3>Resultados encontrados</h3>

<div class="table-responsive">
				<table class="table table-bordered table-striped table-sm">

						<tr>
							<td class="value"><b>#</b></td>
							<#list exibicao as exibir>
								<td class="value"><b>${exibir}</b></td>
							</#list>

						</tr>
						<#if (dados?if_exists?size == 0)>
						<tr>
							<td class="description" colspan="${(exibicao?if_exists?size) + 1}"> N&atilde;o possui dados para esse filtro</td>
						</tr>
						<#else>
						<#list dados as dado>
						<tr>
							<td class="value"><a href="javascript:void(0)" class="btn btn-small btn-success ${classeFuncaoExecutar}" data-id="${dado.util1?c}"><i class="fa fa-check"></i></a></td>
							<#list dado.util2 as imp>
								<td class="description">${imp}</td>
							</#list>

						</tr>
						</#list>
						</#if>

				</table>
</div>
