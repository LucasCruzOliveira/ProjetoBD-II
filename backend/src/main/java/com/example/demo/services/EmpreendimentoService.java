package com.example.demo.services;

import com.example.demo.dao.EmpreendimentoDAO;
import com.example.demo.dto.EmpreendimentoPorLogradouroDTO;
import com.example.demo.dto.EmpreendimentoPorModalidadeDTO;
import com.example.demo.dto.EmpreendimentoPorSituacaoDTO;
import com.example.demo.dto.EmpreendimentosPorRegiaoDTO;
import com.example.demo.enums.Modalidade;
import com.example.demo.enums.Situacao;
import com.example.demo.model.Construtora;
import com.example.demo.model.DadosUH;
import com.example.demo.model.Empreendimento;
import com.example.demo.model.Endereco;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EmpreendimentoService extends ParseService {
    private EmpreendimentoDAO empreendimentoDAO;
    private EnderecoService enderecoService;
    private DadosUHService dadosUHService;
    private ConstrutoraService construtoraService;

    public EmpreendimentoService(CsvService csvService, EmpreendimentoDAO empreendimentoDAO,
                                 EnderecoService enderecoService, DadosUHService dadosUHService,
                                 ConstrutoraService construtoraService) {
        super(csvService);
        this.empreendimentoDAO = empreendimentoDAO;
        this.enderecoService = enderecoService;
        this.dadosUHService = dadosUHService;
        this.construtoraService = construtoraService;
    }

    public void parseToRelational() {
        for(CSVRecord csvRecord : csvService.getRecords()){
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            String textoEndereco  = csvRecord.get("txt_endereco").toUpperCase().trim();
            String cep = csvRecord.get("txt_cep").toUpperCase().trim();
            String nomeMunicipio = csvRecord.get("txt_nome_municipio").toUpperCase().trim();
            String uf = csvRecord.get("txt_sigla_uf").toUpperCase().trim();
            String regiao = csvRecord.get("txt_regiao").toUpperCase().trim();
            String quantidadeUH = csvRecord.get("qtd_uh").trim() ;
            String entregues = csvRecord.get("qtd_uh_entregues").trim().split(",")[0];
            String vigentesUH = csvRecord.get("qtd_uh_vigentes").trim();
            String distratadasUH = csvRecord.get("qtd_uh_distratadas").trim();
            String nomeEmpreendimento = csvRecord.get("txt_nome_empreendimento").trim();
            String nomeConstrutora = csvRecord.get("txt_nome_construtora_entidade");
            String cnpjConstrutora = csvRecord.get("txt_cnpj_construtora_entidade");

            Endereco endereco = enderecoService.parseToRelational(textoEndereco, cep, nomeMunicipio, regiao, uf);
            DadosUH dadosUH = dadosUHService.parseToRelational(quantidadeUH, entregues, vigentesUH, distratadasUH);
            Construtora construtora = construtoraService.parseToRelational(nomeConstrutora, cnpjConstrutora);

            String textoSituacao = csvRecord.get("txt_situacao_empreendimento").toUpperCase().trim();
            for(Situacao situacao : Situacao.values()){
                if(situacao.getDesc().equals(textoSituacao)){
                    textoSituacao = situacao.name();
                }
            }

            Situacao situacao = Situacao.valueOf(textoSituacao);
            LocalDate data = LocalDate.parse(csvRecord.get("dt_assinatura"), dateFormatter);
            String cod_op = csvRecord.get("cod_operacao");
            String valorDesembolsadoTxt =  csvRecord.get("val_desembolsado");
            String valorContratadoTxt = csvRecord.get("val_contratado_total");

            if(cod_op.isEmpty()){
                cod_op = "0";
            }
            if(valorContratadoTxt.isEmpty()){
                valorContratadoTxt = "0.0";
            }
            if(valorDesembolsadoTxt.isEmpty()){
                valorDesembolsadoTxt = "0.0";
            }
            int codigoOperacao = Integer.parseInt(cod_op);

            BigDecimal valorDesembolsado = new BigDecimal(valorDesembolsadoTxt.replace(',', '.'));
            BigDecimal valorTotalContratado = new BigDecimal(valorContratadoTxt.replace(',', '.'));
            String textoModalidade = csvRecord.get("txt_modalidade").toUpperCase();


            if(Modalidade.OFERTA_PUBLICA.getDesc().equals(textoModalidade)){
                textoModalidade = Modalidade.OFERTA_PUBLICA.toString();
            }
            Modalidade modalidade = Modalidade.valueOf(textoModalidade);
            empreendimentoDAO.save(new Empreendimento(nomeEmpreendimento, situacao, data, codigoOperacao, valorDesembolsado, valorTotalContratado, endereco, modalidade, dadosUH, construtora));
        }
    }

    public List<EmpreendimentosPorRegiaoDTO> buscarFinanciamentosPorRegiao() {
        return empreendimentoDAO.buscarEmpreendimentosPorRegiao();
    }

    public List<EmpreendimentoPorSituacaoDTO> buscarEmpreendimentoPorSituacao(){
        return empreendimentoDAO.buscarEmpreendimentoPorSituacao();
    }

    public List<EmpreendimentoPorModalidadeDTO> buscarEmpreendimentoPorModalidade(){
        return empreendimentoDAO.buscarEmpreendimentoPorModalidade();
    }

    public List<EmpreendimentoPorLogradouroDTO> buscarEmpreendimentoPorLogradouro(){
        return empreendimentoDAO.buscarEmpreendimentoPorLogradouro();
    }
}