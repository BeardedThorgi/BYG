package voronoiaoc.byg.common.world.feature.features.overworld.trees.deciduous;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import voronoiaoc.byg.common.world.feature.config.BYGTreeFeatureConfig;
import voronoiaoc.byg.common.world.feature.features.overworld.trees.util.BYGAbstractTreeFeature;

import java.util.Random;
import java.util.Set;

//THIS FEATURE MUST BE REGISTERED & ADDED TO A BIOME!
public class BYGShrub extends BYGAbstractTreeFeature<BYGTreeFeatureConfig> {
    //BYGBlockRenders used for the tree.
    //private static final BlockState LOG = Blocks.OAK_LOG.getDefaultState();
    //private static final BlockState LEAVES = Blocks.OAK_LEAVES.getDefaultState();
    //private static final BlockState LEAVES2 = BYGBlockList.RED_OAK_LEAVES.getDefaultState();
    //private static final BlockState LEAVES3 = BYGBlockList.BROWN_OAK_LEAVES.getDefaultState();
    //private static final BlockState LEAVES4 = BYGBlockList.ORANGE_OAK_LEAVES.getDefaultState();
    //private static final BlockState LEAVES5 = BYGBlockList.YELLOW_BIRCH_LEAVES.getDefaultState();
    private static final BlockState BEENEST = Blocks.BEE_NEST.getDefaultState();

    public BYGShrub(Codec<BYGTreeFeatureConfig> configIn) {
        super(configIn);
    }


    public boolean place(Set<BlockPos> changedBlocks, ISeedReader worldIn, Random rand, BlockPos pos, MutableBoundingBox boundsIn, boolean isSapling, BYGTreeFeatureConfig config) {
        BlockState LOG = config.getTrunkProvider().getBlockState(rand, pos);
        BlockState LEAVES = config.getLeavesProvider().getBlockState(rand, pos);
        int randTreeHeight = 1;
        //Positions
        int posX = pos.getX();
        int posY = pos.getY();
        int posZ = pos.getZ();
        if (posY >= 1 && posY + randTreeHeight + 1 < 256) {

            if (!isDesiredGroundwDirtTag(worldIn, pos.down(), Blocks.GRASS_BLOCK)) {
                return false;
            } else if (!this.isAnotherTreeNearby(worldIn, pos, randTreeHeight, 0, isSapling)) {
                return false;
            } else if (!this.doesSaplingHaveSpaceToGrow(worldIn, pos, randTreeHeight, 5, 5, 5, isSapling)) {
                return false;
            } else {


//Uncommenting this will allow for a 2x2 dirt patch under the tree.
                /*//this.setGroundBlockAt(worldIn, blockpos.east(), pos, Blocks.DIRT.getDefaultState());

                //this.setGroundBlockAt(worldIn, blockpos.south(), pos, Blocks.DIRT.getDefaultState());

                //this.setGroundBlockAt(worldIn, blockpos.south().east(), pos, Blocks.DIRT.getDefaultState());
*/
                //Uncommenting this will allow for a 3x3 dirt patch under the tree.
                /*//this.setGroundBlockAt(worldIn, blockpos.west(), pos, Blocks.DIRT.getDefaultState());

                //this.setGroundBlockAt(worldIn, blockpos.south().west(), pos, Blocks.DIRT.getDefaultState());

                //this.setGroundBlockAt(worldIn, blockpos.north(), pos, Blocks.DIRT.getDefaultState());

                //this.setGroundBlockAt(worldIn, blockpos.north().east(), pos, Blocks.DIRT.getDefaultState());

                //this.setGroundBlockAt(worldIn, blockpos.north().west(), pos, Blocks.DIRT.getDefaultState());
*/


                Direction direction = Direction.Plane.HORIZONTAL.random(rand);
                int randTreeHeight2 = randTreeHeight - rand.nextInt(1);
                int posY1 = 2 - rand.nextInt(1);
                int posX1 = posX;
                int posZ1 = posZ;
                int topTrunkHeight = posY + randTreeHeight - 1;


                for (int buildTrunk = 0; buildTrunk < randTreeHeight; ++buildTrunk) {
                    if (buildTrunk >= randTreeHeight2 && posY1 < 0) {
                        posX1 += direction.getXOffset();
                        posZ1 += direction.getZOffset();
                        ++posY1;
                    }

                    int logplacer = posY + buildTrunk;
                    BlockPos blockpos1 = new BlockPos(posX1, logplacer, posZ1);
                    //These BlockPos' allow you to make trunks thicker than 2x2,


                    //Sets Logs
                    if (isAir(worldIn, blockpos1)) {
                        placeLog(LOG, changedBlocks, worldIn, blockpos1, boundsIn);
                    }
                }

                int leavePreset = rand.nextInt(1) + 1;
                {
                    if (leavePreset == 1) {
                        //This randomizer allows you to have randomly generating sized leave widths(X & Z). You can remove the randomizer and set your own value instead.
                        int leavessquarespos = rand.nextInt(1) + 1;
                        //This loads leaves in squares. Manually placing the squares can allow you to load in perfect squares.
                        for (int posXLeafWidth = -leavessquarespos; posXLeafWidth <= leavessquarespos; ++posXLeafWidth) {//has to do with leaves
                            for (int posZLeafWidthL0 = -leavessquarespos; posZLeafWidthL0 <= leavessquarespos; ++posZLeafWidthL0) {
                                //int leaveheight = 1;//0 lines it up with top log
                                placeLeaves(LEAVES, worldIn, posX1 + posXLeafWidth, topTrunkHeight, posZ1 + posZLeafWidthL0, boundsIn, changedBlocks);

                                placeLeaves(LEAVES, worldIn, posX1 + 1, topTrunkHeight, posZ1, boundsIn, changedBlocks);
                                placeLeaves(LEAVES, worldIn, posX1 + 2, topTrunkHeight, posZ1, boundsIn, changedBlocks);
                                placeLeaves(LEAVES, worldIn, posX1 - 1, topTrunkHeight, posZ1, boundsIn, changedBlocks);
                                placeLeaves(LEAVES, worldIn, posX1 - 2, topTrunkHeight, posZ1, boundsIn, changedBlocks);

                                placeLeaves(LEAVES, worldIn, posX1, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                placeLeaves(LEAVES, worldIn, posX1, topTrunkHeight, posZ1 - 2, boundsIn, changedBlocks);
                                placeLeaves(LEAVES, worldIn, posX1, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                placeLeaves(LEAVES, worldIn, posX1, topTrunkHeight, posZ1 + 2, boundsIn, changedBlocks);

                                placeLeaves(LEAVES, worldIn, posX1 + 1, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                placeLeaves(LEAVES, worldIn, posX1 + 1, topTrunkHeight, posZ1 + 2, boundsIn, changedBlocks);
                                placeLeaves(LEAVES, worldIn, posX1 + 1, topTrunkHeight, posZ1 - 2, boundsIn, changedBlocks);
                                placeLeaves(LEAVES, worldIn, posX1 - 1, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                placeLeaves(LEAVES, worldIn, posX1 - 1, topTrunkHeight, posZ1 + 2, boundsIn, changedBlocks);
                                placeLeaves(LEAVES, worldIn, posX1 - 1, topTrunkHeight, posZ1 - 2, boundsIn, changedBlocks);
                                placeLeaves(LEAVES, worldIn, posX1 - 1, topTrunkHeight, posZ1 - 2, boundsIn, changedBlocks);
                                placeLeaves(LEAVES, worldIn, posX1 + 2, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                placeLeaves(LEAVES, worldIn, posX1 + 2, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                placeLeaves(LEAVES, worldIn, posX1 - 2, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                placeLeaves(LEAVES, worldIn, posX1 - 2, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);

                                //Y+1
                                placeLeaves(LEAVES, worldIn, posX1 + 1, topTrunkHeight + 1, posZ1, boundsIn, changedBlocks);
                                placeLeaves(LEAVES, worldIn, posX1 - 1, topTrunkHeight + 1, posZ1, boundsIn, changedBlocks);
                                placeLeaves(LEAVES, worldIn, posX1, topTrunkHeight + 1, posZ1 + 1, boundsIn, changedBlocks);
                                placeLeaves(LEAVES, worldIn, posX1, topTrunkHeight + 1, posZ1 - 1, boundsIn, changedBlocks);
                                placeLeaves(LEAVES, worldIn, posX1, topTrunkHeight + 1, posZ1, boundsIn, changedBlocks);

                                placeLeaves(LEAVES, worldIn, posX1 + 1, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                placeLeaves(LEAVES, worldIn, posX1 + 1, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                placeLeaves(LEAVES, worldIn, posX1 - 1, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                placeLeaves(LEAVES, worldIn, posX1 - 1, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                            }
                        }
                    }
                }
            }

            return true;
            //}
        } else {
            return false;
        }
    }

    private boolean doesTreeFit(IWorldGenerationBaseReader reader, BlockPos blockPos, int height) {
        int x = blockPos.getX();
        int y = blockPos.getY();
        int z = blockPos.getZ();
        BlockPos.Mutable pos = new BlockPos.Mutable();

        for (int yOffset = 0; yOffset <= height + 1; ++yOffset) {
            //Distance/Density of trees. Positive Values ONLY
            int distance = 2;

            for (int xOffset = -distance; xOffset <= distance; ++xOffset) {
                for (int zOffset = -distance; zOffset <= distance; ++zOffset) {
                    if (!canLogPlaceHere(reader, pos.setPos(x + xOffset, y + yOffset, z + zOffset))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


}