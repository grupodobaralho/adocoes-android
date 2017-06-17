package br.pucrs.ages.adocoes.Model;

import java.io.Serializable;
import java.util.Date;
import br.pucrs.ages.adocoes.Model.types.*;

/**
 * Created by kuquert on 03/06/17.
 */

public class Documento implements Serializable {
    private String numero;
    private Date dataEmissao;
    private String orgaoEmissor;
    private TipoDocumento tipoDocumento;
    private String imagem;
}
