package com.example.demo.services;

import com.example.demo.dao.EmpreendimentoDAO;
import com.example.demo.enums.Modalidade;
import com.example.demo.enums.Situacao;
import com.example.demo.model.Empreendimento;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    @Override
    public void parseToRelational() {
        for(CSVRecord csvRecord : csvService.getRecords()){

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            String nome = csvRecord.get("txt_nome_construtora_entidade").trim();
            String textoSituacao = csvRecord.get("txt_situacao_empreendimento").toUpperCase().trim();

            if(Situacao.CANCELADO.getDesc().equals(textoSituacao)){
                textoSituacao = Situacao.CANCELADO.toString();
            }
            else if (Situacao.CONCLUIDO.getDesc().equals(textoSituacao)){
                textoSituacao = Situacao.CONCLUIDO.toString();
            }
            else if (Situacao.NAO_CONCLUIDO.getDesc().equals(textoSituacao)) {
                textoSituacao = Situacao.NAO_CONCLUIDO.toString();
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

            empreendimentoDAO.save(new Empreendimento(nome, situacao, data, codigoOperacao, valorDesembolsado, valorTotalContratado, modalidade));
        }
    }
}