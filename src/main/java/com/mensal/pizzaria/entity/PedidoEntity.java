package com.mensal.pizzaria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedido", schema = "pizzaria")
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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id", nullable = false)
    private ClienteEntity cliente;

    @Column(nullable = false)
    private boolean delivery;

    @Enumerated(EnumType.STRING)
    @Column
    private Forma_Pagamento formaPagamento;

    @Column(nullable = false)
    private Double total;
}