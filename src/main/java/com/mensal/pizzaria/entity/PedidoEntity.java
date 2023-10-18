package com.mensal.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_pedido", schema = "pizzaria")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "pedido_produto",
            schema = "pizzaria",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<ProdutoEntity> produtoList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id", nullable = false)
    private ClienteEntity cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_funcionario", referencedColumnName = "id", nullable = false)
    private FuncionarioEntity funcionario;

    @Column(nullable = false)
    private boolean delivery;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Forma_Pagamento formaPagamento;

    private Double total;
}
