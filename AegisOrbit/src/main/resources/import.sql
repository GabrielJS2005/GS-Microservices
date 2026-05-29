INSERT INTO tb_regiao_monitorada (nome_regiao, continente, tipo_area, extensao_km2) VALUES ('Polo Norte Magnético', 'Ártico', 'Calota Polar', 14056000.0);
INSERT INTO tb_regiao_monitorada (nome_regiao, continente, tipo_area, extensao_km2) VALUES ('Mar de Svalbard', 'Europa', 'Área Marinha Protegida', 450300.75);
INSERT INTO tb_regiao_monitorada (nome_regiao, continente, tipo_area, extensao_km2) VALUES ('Costa da Península Antártica', 'Antártida', 'Zona Costeira Glacial', 980000.0);
INSERT INTO tb_regiao_monitorada (nome_regiao, continente, tipo_area, extensao_km2) VALUES ('Mar de Beaufort', 'América do Norte', 'Oceano Aberto / Mar Marginal', 476000.5);

INSERT INTO tb_evento_alerta (tipo_evento, severidade, impacto_estimado, data_observacao, id_regiao) VALUES ('Anomalia de Derretimento de Gelo', 3, 45.25, '2026-05-15', 1);
INSERT INTO tb_evento_alerta (tipo_evento, severidade, impacto_estimado, data_observacao, id_regiao) VALUES ('Previsão de Inundação Crítica', 5, 88.00, '2026-05-20', 2);
INSERT INTO tb_evento_alerta (tipo_evento, severidade, impacto_estimado, data_observacao, id_regiao) VALUES ('Desprendimento de Iceberg Massivo', 4, 72.50, '2026-05-24', 3);
INSERT INTO tb_evento_alerta (tipo_evento, severidade, impacto_estimado, data_observacao, id_regiao) VALUES ('Elevação Incomum do Nível do Mar', 2, 15.10, '2026-05-28', 4);