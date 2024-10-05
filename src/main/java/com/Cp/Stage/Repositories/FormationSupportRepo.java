package com.Cp.Stage.Repositories;


import org.springframework.data.repository.ListCrudRepository;

import com.Cp.Stage.Models.FormationSupport;

public interface FormationSupportRepo extends ListCrudRepository<FormationSupport,Long> {

    @Query("SELECT f.titre, f.lienVersFormation FROM FormationSupport f JOIN f.competence c JOIN c.domaineCompetence dc JOIN dc.projet p " +
            "WHERE p.id = :projectId")
    List<FormationSupport> findAllByProjet(@Param("projectId") Long projectId);

    @Query("SELECT f.titre, f.lienVersFormation FROM FormationSupport f JOIN f.competence c JOIN c.domaineCompetence dc " +
            "WHERE dc.titre = :NomDomaine")
    List<FormationSupport> findAllByDomaineCompetence(@Param("NomDomaine") String NomDomaine);
    @Query("SELECT f.titre, f.lienVersFormation FROM FormationSupport f JOIN f.competence c " +
            "WHERE c.titre = :NomCompetence")
    List<FormationSupport> findAllByCompetence(@Param("NomCompetence") String NomCompetence);
    Optional<FormationSupport> findByNom(String NomFormation);

}
