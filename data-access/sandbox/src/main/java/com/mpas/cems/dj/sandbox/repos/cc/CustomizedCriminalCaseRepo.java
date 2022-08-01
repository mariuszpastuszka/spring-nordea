package com.mpas.cems.dj.sandbox.repos.cc;

        import com.mpas.cems.dao.CriminalCase;
        import com.mpas.cems.dao.Detective;

        import java.util.List;


public interface CustomizedCriminalCaseRepo {

    List<Detective> getTeam(CriminalCase criminalCase);
}
