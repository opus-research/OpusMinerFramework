/*
 * Copyright 2019 danie.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File;
import java.util.ArrayList;
import les.opus.framework.CollectorFramework;
import les.opus.framework.repository.AnalysisRepository;
import les.opus.framework.repository.Source;
import les.opus.framework.tool.Tool;
import les.opus.framework.tool.ToolRegister;
import les.opus.framework.tool.ToolState;
import les.opus.framework.tool.state.ExecutionState;
import les.opus.framework.tool.state.PosExecutionState;
import les.opus.framework.tool.state.PreExecutionState;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tool.TestCommand;
import tool.Tool1;
import tool.Tool2;

/**
 *
 * @author danie
 */
public class RepositoryMiningTest {

    private static Tool tool1;
    private static Tool tool2;
    private static ToolRegister register;
    private static ArrayList<Boolean> innerAsserts = new ArrayList<>();

    private static String onlinerepositoryUrl = "https://github.com/danieltmo/calculator-angular.git";
    private static String localrepositoryUrl = "./test-repo/git-1";

    private static AnalysisRepository onlineRepository;
    private static AnalysisRepository localRepository;

    public RepositoryMiningTest() {
    }

    /*
    * Define items that will be used in the tests
     */
    @BeforeClass
    public static void setUpClass() {

        //New instance of the tools
        tool1 = new Tool1();
        tool2 = new Tool2();

        //Register the tools that will be used
        register = new ToolRegister();
        register.registerTool(tool1);
        register.registerTool(tool2);

        //Define the online repository
        onlineRepository = AnalysisRepository.buildRepository(onlinerepositoryUrl, Source.GIT,
                onlinerepositoryUrl.substring(onlinerepositoryUrl.lastIndexOf("/") + 1));

        //Define the local repository
        localRepository = AnalysisRepository.buildRepository(localrepositoryUrl, Source.LOCAL,
                localrepositoryUrl.substring(localrepositoryUrl.lastIndexOf("/") + 1));
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {

        tool1.getCommands().clear();
        tool2.getCommands().clear();
        innerAsserts.clear();
    }

    @Test
    public void shouldGetInfoFromAGitRepo() {

        tool1.addCommand(new TestCommand() {

            @Override
            public void run(ToolState state) {
                if (state instanceof ExecutionState) {
                    ExecutionState preState = (ExecutionState) state;
                    innerAsserts.add(preState.getRepo().getFirstCommit().equals("5152314b8867645f4740c68bb8f5e2cf9c72502f"));
                }
            }
        });

        try {
            //Run the framework
            CollectorFramework.GetInstance()
                    .repository(onlineRepository)
                    .tools(register)
                    .run();
        } catch (Exception e) {
            Assert.fail();
        }
        for (Boolean b : innerAsserts) {
            Assert.assertTrue(b);
        }
    }

    @Test
    public void shouldAccessAllDefaultState() {

        tool1.addCommand(new TestCommand() {
            boolean accessedPre = false;
            boolean accessedDuring = false;
            boolean accessedPos = false;

            @Override
            public void run(ToolState state) {
                if (state instanceof ExecutionState) {
                    accessedDuring = true;
                } else if (state instanceof PreExecutionState) {
                    accessedPre = true;
                } else if (state instanceof PosExecutionState) {
                    accessedPos = true;

                    innerAsserts.add(accessedPre);
                    innerAsserts.add(accessedDuring);
                    innerAsserts.add(accessedPos);

                }
            }
        });

        try {
            //Run the framework
            CollectorFramework.GetInstance()
                    .repository(localRepository)
                    .tools(register)
                    .run();
        } catch (Exception e) {
            Assert.fail();
        }

        for (Boolean b : innerAsserts) {
            Assert.assertTrue(b);
        }

    }

    @Test
    public void countCommitsFromAGitRepo() {

        tool1.addCommand(new TestCommand() {
            int totalCommit;

            @Override
            public void run(ToolState state) {
                if (state instanceof ExecutionState) {
                    totalCommit++;
                } else if (state instanceof PosExecutionState) {
                    innerAsserts.add(totalCommit == 2);
                }
            }
        });

        try {
            //Run the framework
            CollectorFramework.GetInstance()
                    .repository(onlineRepository)
                    .tools(register)
                    .run();
        } catch (Exception e) {
            Assert.fail();
        }

        for (Boolean b : innerAsserts) {
            Assert.assertTrue(b);
        }

    }

    @Test
    public void countCommitsFromALocalRepo() {

        tool1.addCommand(new TestCommand() {
            int totalCommit;

            @Override
            public void run(ToolState state) {

                if (state instanceof ExecutionState) {
                    totalCommit++;
                } else if (state instanceof PosExecutionState) {
                    innerAsserts.add(totalCommit == 14);
                }
            }
        });

        try {
            //Run the framework
            CollectorFramework.GetInstance()
                    .repository(localRepository)
                    .tools(register)
                    .run();
        } catch (Exception e) {
            Assert.fail();
        }

        for (Boolean b : innerAsserts) {
            Assert.assertTrue(b);
        }

    }

    @Test
    public void accessingAllCommits() {

        tool1.addCommand(new TestCommand() {
            int totalCommit;
            String lastCommit = "";

            @Override
            public void run(ToolState state) {
                if (state instanceof PreExecutionState) {
                    PreExecutionState preState = (PreExecutionState) state;
                    innerAsserts.add(preState.getRepo().getFirstCommit().equals("866e997a9e44cb4ddd9e00efe49361420aff2559"));
                } else if (state instanceof ExecutionState) {
                    ExecutionState exeState = (ExecutionState) state;
                    totalCommit++;
                    lastCommit = exeState.getCommit().getHash();
                } else if (state instanceof PosExecutionState) {
                    innerAsserts.add(lastCommit.equals("186cc64c1cb6e41f5d4adf060edf4db4da326362"));
                    innerAsserts.add(totalCommit == 14);
                }
            }
        });

        try {
            //Run the framework
            CollectorFramework.GetInstance()
                    .repository(localRepository)
                    .tools(register)
                    .run();
        } catch (Exception e) {
            Assert.fail();
        }
        for (Boolean b : innerAsserts) {
            Assert.assertTrue(b);
        }
    }

    @Test
    public void accessingSingleCommits() {
        tool1.addCommand(new TestCommand() {

            @Override
            public void run(ToolState state) {
                if (state instanceof ExecutionState) {
                    ExecutionState exeState = (ExecutionState) state;
                    innerAsserts.add(exeState.getCommit().getHash().equals("186cc64c1cb6e41f5d4adf060edf4db4da326362"));
                }
            }
        });

        try {
            //Run the framework
            CollectorFramework.GetInstance()
                    .repository(localRepository)
                    .tools(register)
                    .single("186cc64c1cb6e41f5d4adf060edf4db4da326362")
                    .run();
        } catch (Exception e) {
            Assert.fail();
        }
        for (Boolean b : innerAsserts) {
            Assert.assertTrue(b);
        }
    }

    @Test
    public void accessingCommitAuthor() {
        tool1.addCommand(new TestCommand() {

            @Override
            public void run(ToolState state) {
                if (state instanceof ExecutionState) {
                    ExecutionState exeState = (ExecutionState) state;
                    if (exeState.getCommit().getHash().equals("186cc64c1cb6e41f5d4adf060edf4db4da326362")) {
                        innerAsserts.add(exeState.getCommit().getAuthor().getName().equals("danieltmo"));
                    } else {
                        innerAsserts.add(exeState.getCommit().getAuthor().getName().equals("Maurício Aniche"));
                    }
                }
            }
        });

        try {
            //Run the framework
            CollectorFramework.GetInstance()
                    .repository(localRepository)
                    .tools(register)
                    .run();
        } catch (Exception e) {
            Assert.fail();
        }
        for (Boolean b : innerAsserts) {
            Assert.assertTrue(b);
        }
    }

    @Test
    public void savingLocalRepository() {

        tool1.addCommand(new TestCommand() {

            @Override
            public void run(ToolState state) {
                if (state instanceof PosExecutionState) {
                    PosExecutionState exeState = (PosExecutionState) state;
                    File f = new File("./projects/git-1");
                    innerAsserts.add(f.exists());
                    innerAsserts.add(f.listFiles().length == 14);
                }
            }
        });
        try {
            //Run the framework
            CollectorFramework.GetInstance()
                    .repository(localRepository)
                    .tools(register)
                    .saveLocalGit()
                    .run();
        } catch (Exception e) {
            Assert.fail();
        }
        for (Boolean b : innerAsserts) {
            Assert.assertTrue(b);
        }
    }

}
