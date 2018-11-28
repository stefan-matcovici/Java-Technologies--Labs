package ro.uaic.info.javatechnologies.optcourses.beans.optionalPackage;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import ro.uaic.info.javatechnologies.optcourses.beans.BackingBean;
import ro.uaic.info.javatechnologies.optcourses.models.OptionalPackage;
import ro.uaic.info.javatechnologies.optcourses.models.Semester;
import ro.uaic.info.javatechnologies.optcourses.repository.OptionalPackageRepository;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Named("optPackagesView")
@ViewScoped
public class OptionalPackagesView extends  BackingBean<OptionalPackage, String> implements Serializable {
    private TreeNode root;

    public OptionalPackagesView() {
        super();
        repository = new OptionalPackageRepository();
    }

    @PostConstruct
    public void init() {
        super.init();
        root = new DefaultTreeNode("Years", null);

        List<OptionalPackage> optionalPackages = null;
        try {
            optionalPackages = repository.getAll();
        } catch (SQLException | MalformedURLException e) {
            e.printStackTrace();
        }
        Set<Integer> years = optionalPackages.stream().map(OptionalPackage::getYear).collect(Collectors.toSet());
        for (Integer year: years) {
            TreeNode yearNode = new DefaultTreeNode(year, root);
            Set<Semester> semesters = optionalPackages.stream().filter(optionalPackage -> optionalPackage.getYear().equals(year))
                    .map(OptionalPackage::getSemester).collect(Collectors.toSet());
            for (Semester semester: semesters) {
                TreeNode semesterNode = new DefaultTreeNode(semester.getName(), yearNode);
                optionalPackages.stream().filter(optionalPackage -> optionalPackage.getYear().equals(year) && optionalPackage.getSemester().equals(semester))
                        .map(OptionalPackage::getCode)
                        .forEach(courseId -> {
                            TreeNode courseNode = new DefaultTreeNode(courseId, semesterNode);
                        });
            }

        }

    }

    public TreeNode getRoot() {
        return root;
    }
}
