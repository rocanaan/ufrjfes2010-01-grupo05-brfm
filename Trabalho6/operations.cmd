use editoraABC.use

!create dEnvio:Date
!set dEntrega.dia := 1
!set dEntrega.mes := 1
!set dEntrega.ano := 2009

!create dValidade:Date
!set dValidade.dia := 1
!set dValidade.mes := 1
!set dValidade.ano := 2011

!create prazoPgmt:Date
!set prazoPgmt.dia := 31
!set prazoPgmt.mes := 1
!set prazoPgmt.ano := 2009

!create prazoContrato:Date
!set prazoContrato.dia := 1
!set prazoContrato.mes := 1
!set prazoContrato.ano := 2011

!create dDev:Date
!set dDev.dia := 5
!set dDev.mes := 1
!set dDev.ano := 2009

!create dRelCliente:Date
!set dRelCliente.dia := 1
!set dRelCliente.mes := 1
!set dRelCliente.ano := 2009

!create dRelAutor:Date
!set dRelAutor.dia := 1
!set dRelAutor.mes := 3
!set dRelAutor.ano := 2009

!create dRelContabilidade:Date
!set dRelContabilidade.dia := 31
!set dRelContabilidade.mes := 1
!set dRelContabilidade.ano := 2009

!create JKRowling:Autor
!set JKRowling.nome := 'J.K.Rowling'
!set JKRowling.salary := 100

!create HarryP:Manuscrito
!set HarryP.nome := 'Harry Potter'
!set HarryP.descricao := 'As aventuras de um jovem bruxo.'

!create JackDaniels:Editor
!set JackDaniels.nome := 'Jack Daniels'

!create HPFilosofal:Livro
!set HPFilosofal.nome := 'Harry Potter e a pedra filosofal'
!set HPFilosofal.preco := 12.00
!set HPFilosofal.prazoEntrega := @dEntrega
!set HPFilosofal.disponibilidade := true
!set HPFilosofal.estado := true

!create XPS:Grafica 
!set XPS.nome := 'X Printer Service'
!set XPS.cotacao := @orcamento

!create estoque:Estoque
!set estoque.produto := @HPFilosofal
!set estoque.qtd := 20
!set estoque.relatorio := @Contabilidade
!set estoque.enviaPedido := true

!create pgmnto:Pagamento
!set pgmnto.valorTotal := 15.00
!set pgmnto.valor := 12.00
!set pgmnto.dataPgt := @prazoPgmt

!create comercial:Comercial
!set comercial.autorizacao := true
!set comercial.validade := @dValidade

!create fatura:Fatura
!set fatura.id := 1
!set fatura.nomeCliente := 'Luis'
!set fatura.valor := 15.00
!set fatura.formaPgmt := 2
!set fatura.prazo := @prazoPgmt

!create transportadora:Transportadora
!set transportadora.nome := 'Carros S.A.'
!set transportadora.custo := 12.00
!set transportadora.frete := 3.00
!set transportadora.dataEnvio := @dEnvio

!create orcamento:Cotacao
!set orcamento.grafica := @XPS
!set orcamento.livro := @HPFilosofal
!set orcamento.valorImpressao := 8.00

!create luis:Cliente
!set luis.nome := 'Luis'
!set luis.endereço := 'R. Sobe e Desce, 24'
!set luis.fatura := @fatura
!set luis.relatorio := @RelCliente
!set luis.recebeu := true
!set luis.qtdVendida := 5

!create pedidoLuis:PedidoCompra
!set pedidoLuis.livros := @HPFilosofal
!set pedidoLuis.qtd := 7
!set pedidoLuis.formaEnvio := 1 
!set pedidoLuis.valorTotal := 84.00
!set pedidoLuis.status := 'Enviado'

!create contabil:Contabilidade
!set contabil.relatorio := @Contabilidade
!set contabil.valorTotal := 200.00

!create contrato:Contrato
!set contrato.txDirAutoral := 2
!set contrato.prazo := @prazoContrato
!set contrato.divDirAutoral:= 1

!create pedidoDevolucao:PedidoDevolucao
!set pedidoDevolucao.nomeCliente := 'Luis'
!set pedidoDevolucao.produto := @HPFilosofal
!set pedidoDevolucao.motivo := 'Livro estragado'
!set pedidoDevolucao.dataDev := @dDev

!create RelCliente:paraCliente
!set RelCliente.data := @dRelCliente
!set RelCliente.assunto := 'Relatorio sobre pedidos'
!set RelCliente.pedidos := @pedidoLuis
!set RelCliente.faturas := @fatura
!set RelCliente.pagamentos := @pgmnto
!set RelCliente.devolucoes := @pedidoDevolucao

!create RelAutor:paraAutor
!set RelAutor.data := @dRelAutor
!set RelAutor.assunto := 'Avaliacao sobre o desempenho financeiro'
!set RelAutor.totalVendaLivro := 100.00
!set RelAutor.dinheiroRecebido := 90.00
!set RelAutor.qtdLivroEstoque := 0

!create contabilidade:paraContabilidade
!set contabilidade.data := @dRelContabilidade
!set contabilidade.assunto := 'Relatorio informativo de estoque'
!set contabilidade.totalVenda := 120.00
!set contabilidade.totalPago := 90.00
!set contabilidade.qtdTotalEstoque := 20