--SOC
  SELECT * FROM soc
  ORDER BY id;

  SELECT * FROM soc
  WHERE id = 1;

 --INFORMEINCIDENTE

  SELECT * FROM informeincidente
  ORDER BY id;

  SELECT * FROM informeincidente
  WHERE id = 1;

  --INCIDENTE
  SELECT * FROM incidente
  ORDER BY id;

  SELECT * FROM incidente
  WHERE id = 1;

  SELECT *  FROM incidente
  WHERE socid = ?
  ORDER BY id ;

  SELECT incidente.*, informeincidente.id AS informeid, informeincidente.malwaredetectado, informeincidente.nivelseveridad, informeincidente.conclusion
           FROM incidente
            INNER JOIN informeincidente ON informeincidente.incidenteid = incidente.id
            WHERE incidente.id = 1;


   SELECT incidente.*,
            soc.nombre AS socnombre, soc.pais, soc.nivelseguridad,
            informeincidente.id AS informeid, informeincidente.malwaredetectado, informeincidente.nivelseveridad, informeincidente.conclusion
                      FROM incidente
                      INNER JOIN soc ON soc.id = incidente.socid
                      INNER JOIN informeincidente ON informeincidente.incidenteid = incidente.id
                      WHERE informeincidente.malwaredetectado = true
                      AND informeincidente.nivelseveridad > 90
                      AND soc.pais = 'España';


