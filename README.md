

## Projeto Android: BookByCode
Projeto Android para disciplina de Programação para Dispositivos Moveis (PDM). 

### Dependências
 - Leitor de QR Code [Barcode](https://github.com/varvet/BarcodeReaderSample)
 - [Botões interativos](https://github.com/dmytrodanylyk/android-process-button)


### Descrição
Para agilizar no processo de agendamento de reserva de livros que não estão disponíveis no momento, o aplicativo BookByCode utiliza a leitura de um código (QR Code) nos livros da biblioteca e acessa o sistema que por sua vez reserva o livro para a próxima data em que algum exemplar deste livro estiver disponível.
#### Contexto
Normalmente é mantido na biblioteca um exemplar de cada livro **para consulta**, que não pode ser reservado. Este livro serve para que os alunos possam consultar as informações quando os demais exemplares do mesmo livro já estão "emprestados".  
No sistema da biblioteca é possível localizar o livro e realizar o **agendamento** da reserva, e quando um exemplar (**para reserva**) for devolvido, o usuário é notificado e o livro é bloqueado para que apenas ele possa reservar (caso ele não realize a reserva em um tempo X, o agendamento é cancelado).

#### Problema
Para agendar um reserva, um usuário precisar acessar o sistema, localizar o livro por meio de uma pesquisa e realizar o agendamento. Esse procedimento requer o acesso ao computador (pois fica é mais trabalhoso a busca do livro e login no sistema por meio de um celular). 

#### Solução proposta
Com uma etiqueta contendo um código (QR Code), o usuário pode utilizar o aplicativo BookByCode para acessar os dados do livro e ir direto à funcionalidade de agendamento de reserva. Agilizando ainda mais esta tarefa, por meio do aplicativo também é possível visualizar os livros agendados, informações sobre os livros e disponibilidade de versões online oferecidas pela biblioteca.


