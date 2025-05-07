// package com.juriscontrol.demo.service;

// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.nio.file.StandardCopyOption;
// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;

// import com.juriscontrol.demo.config.ArquivoConfig;
// import com.juriscontrol.demo.dto.AnexoDTO.ArquivoDTO.ArquivoDTO;

// @Service
// public class ArquivoService {

// private final Path diretorio;

// public ArquivoService(ArquivoConfig arquivoConfig) {
// this.diretorio =
// Paths.get(arquivoConfig.getCarregarDiretorio()).toAbsolutePath().normalize();
// }

// //carregar arquivo
// public ArquivoDTO carregarArquivo(MultipartFile anexo) throws IOException {

// // Verifica se o anexo está vazio
// if(anexo == null || anexo.isEmpty()) {
// throw new IOException("O arquivo anexado está vazio ou é inválido.");
// }

// //Verifica se o diretório já existe
// if(!Files.exists(this.diretorio)) {
// Files.createDirectories(diretorio);
// }

// // Define o caminho do arquivo
// Path caminhoArquivo = diretorio.resolve(anexo.getOriginalFilename());

// // Verifica se o arquivo já existe
// if(Files.exists(caminhoArquivo)) {
// throw new IOException("Arquivo " + caminhoArquivo.getFileName() + " já
// existe.");
// }

// // Salva o anexo no disco (salva o fluxo de bytes com o caminho do arquivo)
// Files.copy(anexo.getInputStream(), caminhoArquivo);

// // Retorna em DTO os metadados
// return new ArquivoDTO(
// anexo.getOriginalFilename(),
// anexo.getContentType(),
// anexo.getSize(),
// caminhoArquivo.getFileName().toString()
// );
// }

// //carregar vários arquivos*
// public List<ArquivoDTO> carregarVariosArquivos(List<MultipartFile> anexos)
// throws IOException {

// // Cria uma lista para armazenar os arquivos
// List<ArquivoDTO> arquivos = new ArrayList<>();

// // Faz uma iteração (tipo each loop) na lista de anexos chamando a função
// "carregarArquivo"
// for(MultipartFile anexo : anexos) {
// arquivos.add(carregarArquivo(anexo));
// }

// // Retorna a lista dos arquivos
// return arquivos;
// }

// //atualizar arquivo
// public ArquivoDTO atualizarArquivo(MultipartFile novoAnexo, String nomeAnexo)
// throws IOException {

// // Verifica se o anexo está vazio
// if(novoAnexo == null || novoAnexo.isEmpty()) {
// throw new IOException("O arquivo anexado está vazio ou é inválido.");
// }

// // Verifica se o diretório já existe e armazena o caminho dele
// Path caminhoAntigo = buscarArquivo(nomeAnexo);

// // Verifica se o arquivo antigo existe
// if(!Files.exists(caminhoAntigo)) {
// throw new IOException("Arquivo " + caminhoAntigo.getFileName() + " não
// encontrado.");
// }

// // Salva o anexo no disco, mantendo o nome do antigo
// Files.copy(
// novoAnexo.getInputStream(),
// caminhoAntigo,
// StandardCopyOption.REPLACE_EXISTING
// );

// // Retorna em DTO os metadados atualizados
// return new ArquivoDTO(
// novoAnexo.getOriginalFilename(),
// novoAnexo.getContentType(),
// novoAnexo.getSize(),
// caminhoAntigo.toUri().toString()
// );
// }

// //buscar arquivo
// public Path buscarArquivo(String nomeAnexo) {

// return diretorio.resolve(nomeAnexo).normalize();
// }

// //buscar todos os arquivos
// public List<ArquivoDTO> buscarTodosArquivos() throws IOException {

// // Verifica se o diretório é um diretório
// if (!Files.isDirectory(diretorio)) {
// throw new IOException("Diretório inválido: " + diretorio);
// }

// // Busca todos os arquivos no diretório
// List<Path> listaCaminhos = Files.list(diretorio)
// .filter(Files::isRegularFile)
// .toList();

// // Converte os arquivos para DTO
// List<ArquivoDTO> listaArquivos = new ArrayList<>();
// for (Path caminhoArquivo : listaCaminhos) {
// listaArquivos.add(new ArquivoDTO(
// caminhoArquivo.getFileName().toString(), // Nome
// Files.probeContentType(caminhoArquivo), // Tipo MIME (Tipo do arquivo)
// Files.size(caminhoArquivo), // Tamanho
// caminhoArquivo.toUri().toString() // URI (Caminho absoluto)
// ));
// }
// return listaArquivos;
// }
// /**
// return Files.list(diretorio)
// .filter(Files::isRegularFile)
// .map(caminhoArquivo -> new ArquivoDTO(
// caminhoArquivo.getFileName().toString(),
// Files.probeContentType(caminhoArquivo),
// Files.size(caminhoArquivo),
// caminhoArquivo.toUri().toString()
// ))
// .toList();

// String tipo = Files.probeContentType(caminhoArquivo);
// if (tipo == null) {
// tipo = "application/octet-stream"; // Tipo genérico
// }

// //ler arquivo*
// public byte[] lerArquivo(String caminhoArquivo) throws IOException {

// return Files.readAllBytes(buscarArquivo(caminhoArquivo));
// }

// //deletar arquivo
// public void excluirArquivo(String caminhoArquivo) throws IOException {

// Path caminho = buscarArquivo(caminhoArquivo);
// Files.deleteIfExists(caminho);
// }

// private void criarDiretorioSeNaoExistir() throws IOException {
// if (!Files.exists(diretorioBase)) {
// Files.createDirectories(diretorioBase);
// }
// }

// private void validarArquivo(MultipartFile arquivo) throws IOException {
// if (arquivo == null || arquivo.isEmpty()) {
// throw new IOException("Arquivo inválido ou vazio");
// }
// }

// private Path gerarCaminhoUnico(String nomeOriginal) {
// Path caminho = diretorioBase.resolve(nomeOriginal);

// // Evita sobrescrita adicionando sufixo (ex: "arquivo(1).pdf")
// int contador = 0;
// while (Files.exists(caminho)) {
// contador++;
// String novoNome = String.format("%s(%d)%s",
// getNomeSemExtensao(nomeOriginal),
// contador,
// getExtensao(nomeOriginal));
// caminho = diretorioBase.resolve(novoNome);
// }

// return caminho;
// }

// private ArquivoDTO toDto(MultipartFile arquivo, Path caminho) {
// return new ArquivoDTO(
// caminho.getFileName().toString(),
// arquivo.getContentType(),
// arquivo.getSize(),
// caminho.toString()
// );
// }

// // Utilitários para manipulação de nomes de arquivos
// private String getNomeSemExtensao(String nomeArquivo)
// private String getExtensao(String nomeArquivo)

// }
