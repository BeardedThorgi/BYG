package voronoiaoc.byg.common.world.feature.features.overworld.trees.jacaranda;

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
public class IndigoJacarandaTree extends BYGAbstractTreeFeature<BYGTreeFeatureConfig> {
    //private static final BlockState LOG = BYGBlockList.JACARANDA_LOG.getDefaultState();
    //private static final BlockState LEAVES = BYGBlockList.INDIGO_JACARANDA_LEAVES.getDefaultState();
    private static final BlockState BEENEST = Blocks.BEE_NEST.getDefaultState();

    public IndigoJacarandaTree(Codec<BYGTreeFeatureConfig> configIn) {
        super(configIn);
        //setSapling((net.minecraftforge.common.IPlantable) BYGBlockList.JACARANDA_SAPLING);
    }

    protected static boolean canTreeReplace(IWorldGenerationBaseReader genBaseReader, BlockPos blockPos) {
        return canLogPlaceHere(genBaseReader, blockPos
        );
    }

    public boolean place(Set<BlockPos> changedBlocks, ISeedReader worldIn, Random rand, BlockPos pos, MutableBoundingBox boundsIn, boolean isSapling, BYGTreeFeatureConfig config) {
        BlockState LOG = config.getTrunkProvider().getBlockState(rand, pos);
        BlockState LEAVES = config.getLeavesProvider().getBlockState(rand, pos);
        int randTreeHeight = rand.nextInt(3) + rand.nextInt(5) + 9;
        //Positions
        int posX = pos.getX();
        int posY = pos.getY();
        int posZ = pos.getZ();
        if (posY >= 1 && posY + randTreeHeight + 1 < 256) {
            BlockPos blockposdown = pos.down();
            if (!isDesiredGroundwDirtTag(worldIn, blockposdown, Blocks.GRASS_BLOCK)) {
                return false;
            } else {

                //this.setGroundBlockAt(worldIn, blockposdown, pos, Blocks.DIRT.getDefaultState());
//                //this.setGroundBlockAt(worldIn, blockposdown.west(), pos, Blocks.DIRT.getDefaultState());

//                //this.setGroundBlockAt(worldIn, blockposdown.east(), pos, Blocks.DIRT.getDefaultState());

//                //this.setGroundBlockAt(worldIn, blockposdown.south(), pos, Blocks.DIRT.getDefaultState());

//                //this.setGroundBlockAt(worldIn, blockposdown.north(), pos, Blocks.DIRT.getDefaultState());


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
                    int logplacer2 = posY;
                    int logplacer3 = posY + randTreeHeight - 3;
                    int logplacer4 = posY + randTreeHeight - 1;
                    int logplacer5 = posY + randTreeHeight - 2;

                    BlockPos blockpos1 = new BlockPos(posX1, logplacer, posZ1);
                    BlockPos blockpos2 = new BlockPos(posX1, logplacer2, posZ1);
                    BlockPos blockpos3 = new BlockPos(posX1, logplacer3, posZ1);
                    BlockPos blockpos4 = new BlockPos(posX1, logplacer4, posZ1);
                    BlockPos blockpos5 = new BlockPos(posX1, logplacer5, posZ1);


                    int logPreset = rand.nextInt(14) + 1;

                    placeLog(LOG, changedBlocks, worldIn, blockpos1, boundsIn);
                    if (rand.nextInt(3) == 0) {
                        placeLog(LOG, changedBlocks, worldIn, blockpos2.south(), boundsIn);
                        placeLog(LOG, changedBlocks, worldIn, blockpos2.north(), boundsIn);
                        if (logPreset == 0) {
                            placeLog(LOG, changedBlocks, worldIn, blockpos2.south().up(), boundsIn);
                        } else if (logPreset == 2) {
                            placeLog(LOG, changedBlocks, worldIn, blockpos2.north().up(), boundsIn);
                        } else if (logPreset == 10) {
                            placeLog(LOG, changedBlocks, worldIn, blockpos2.west().up(), boundsIn);
                        }

                        if (rand.nextInt(3) == 1) {
                            placeLog(LOG, changedBlocks, worldIn, blockpos2.east(), boundsIn);
                        } else if (rand.nextInt(4) == 1) {
                            placeLog(LOG, changedBlocks, worldIn, blockpos2.west(), boundsIn);
                        }
                    }
                    if (rand.nextInt(3) == 2) {
                        placeLog(LOG, changedBlocks, worldIn, blockpos2.west(), boundsIn);
                    }
                    placeLog(LOG, changedBlocks, worldIn, blockpos3.west(), boundsIn);
                    placeLog(LOG, changedBlocks, worldIn, blockpos3.south(), boundsIn);
                    placeLog(LOG, changedBlocks, worldIn, blockpos3.east(), boundsIn);
                    placeLog(LOG, changedBlocks, worldIn, blockpos3.north(), boundsIn);
                    placeLog(LOG, changedBlocks, worldIn, blockpos3.west(2), boundsIn);
                    placeLog(LOG, changedBlocks, worldIn, blockpos3.south(2), boundsIn);
                    placeLog(LOG, changedBlocks, worldIn, blockpos3.east(2), boundsIn);
                    placeLog(LOG, changedBlocks, worldIn, blockpos3.north(2), boundsIn);

                    placeLog(LOG, changedBlocks, worldIn, blockpos4.west(2), boundsIn);
                    placeLog(LOG, changedBlocks, worldIn, blockpos4.south(2), boundsIn);
                    placeLog(LOG, changedBlocks, worldIn, blockpos4.east(2), boundsIn);
                    placeLog(LOG, changedBlocks, worldIn, blockpos4.north(2), boundsIn);

                    placeLog(LOG, changedBlocks, worldIn, blockpos5.west(3), boundsIn);
                    placeLog(LOG, changedBlocks, worldIn, blockpos5.south(3), boundsIn);
                    placeLog(LOG, changedBlocks, worldIn, blockpos5.east(3), boundsIn);
                    placeLog(LOG, changedBlocks, worldIn, blockpos5.north(3), boundsIn);
                    placeLog(LOG, changedBlocks, worldIn, blockpos5.west(4), boundsIn);
                    placeLog(LOG, changedBlocks, worldIn, blockpos5.south(4), boundsIn);
                    placeLog(LOG, changedBlocks, worldIn, blockpos5.east(4), boundsIn);
                    placeLog(LOG, changedBlocks, worldIn, blockpos5.north(4), boundsIn);

                    placeLog(LOG, changedBlocks, worldIn, blockpos5.north(5).down().west(), boundsIn);
                    placeLog(LOG, changedBlocks, worldIn, blockpos5.east(5).down().north(), boundsIn);
                    placeLog(LOG, changedBlocks, worldIn, blockpos5.south(5).down().east(), boundsIn);
                    placeLog(LOG, changedBlocks, worldIn, blockpos5.west(5).down().south(), boundsIn);

                }
                int leavePreset = rand.nextInt(1) + 1;
                {
                    if (leavePreset == 1) {
                        int leavessquarespos = 1;
                        for (int posXLeafWidth = -leavessquarespos; posXLeafWidth <= leavessquarespos; ++posXLeafWidth) {//has to do with leaves
                            for (int posZLeafWidthL0 = -leavessquarespos; posZLeafWidthL0 <= leavessquarespos; ++posZLeafWidthL0) {
                                for (int posYLeafHeight = 0; posYLeafHeight <= 3; ++posYLeafHeight) {
                                    placeLeaves(LEAVES, worldIn, posX1 + posXLeafWidth, topTrunkHeight + 1, posZ1 + posZLeafWidthL0, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 + 2, topTrunkHeight + 1, posZ1, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 - 2, topTrunkHeight + 1, posZ1, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1, topTrunkHeight + 1, posZ1 + 2, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1, topTrunkHeight + 1, posZ1 - 2, boundsIn, changedBlocks);

                                    placeLeaves(LEAVES, worldIn, posX1 + posXLeafWidth, topTrunkHeight + posYLeafHeight - 3, posZ1 + posZLeafWidthL0, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1, topTrunkHeight + posYLeafHeight - 6, posZ1 - 1, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1, topTrunkHeight + posYLeafHeight - 3, posZ1 - 2, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1, topTrunkHeight + posYLeafHeight - 2, posZ1 - 3, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1, topTrunkHeight + posYLeafHeight - 3, posZ1 - 4, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1, topTrunkHeight + posYLeafHeight - 5, posZ1 - 4, boundsIn, changedBlocks);

                                    if (posYLeafHeight <= 2) {
                                        placeLeaves(LEAVES, worldIn, posX1, topTrunkHeight + posYLeafHeight - 3, posZ1 - 5, boundsIn, changedBlocks);
                                    }
                                    if (posYLeafHeight <= 1) {
                                        placeLeaves(LEAVES, worldIn, posX1 - 1, topTrunkHeight + posYLeafHeight - 2, posZ1 - 4, boundsIn, changedBlocks);
                                        placeLeaves(LEAVES, worldIn, posX1 - 1, topTrunkHeight + posYLeafHeight - 1, posZ1 - 3, boundsIn, changedBlocks);
                                    }
                                    if (posYLeafHeight <= 2) {
                                        placeLeaves(LEAVES, worldIn, posX1 - 1, topTrunkHeight + posYLeafHeight - 2, posZ1 - 2, boundsIn, changedBlocks);
                                        placeLeaves(LEAVES, worldIn, posX1 + 2, topTrunkHeight + posYLeafHeight - 5, posZ1 - 4, boundsIn, changedBlocks);
                                        placeLeaves(LEAVES, worldIn, posX1 + 1, topTrunkHeight + posYLeafHeight - 3, posZ1 - 3, boundsIn, changedBlocks);
                                        placeLeaves(LEAVES, worldIn, posX1 + 1, topTrunkHeight + posYLeafHeight - 2, posZ1 - 2, boundsIn, changedBlocks);
                                        placeLeaves(LEAVES, worldIn, posX1 + 2, topTrunkHeight + posYLeafHeight - 2, posZ1 - 1, boundsIn, changedBlocks);

                                        placeLeaves(LEAVES, worldIn, posX1 + 3, topTrunkHeight + posYLeafHeight - 2, posZ1, boundsIn, changedBlocks);
                                        placeLeaves(LEAVES, worldIn, posX1 + 4, topTrunkHeight + posYLeafHeight - 2, posZ1, boundsIn, changedBlocks);
                                        placeLeaves(LEAVES, worldIn, posX1 + 5, topTrunkHeight + posYLeafHeight - 3, posZ1, boundsIn, changedBlocks);

                                        placeLeaves(LEAVES, worldIn, posX1 + 6, topTrunkHeight + posYLeafHeight - 4, posZ1 - 1, boundsIn, changedBlocks);
                                        placeLeaves(LEAVES, worldIn, posX1 + 5, topTrunkHeight + posYLeafHeight - 5, posZ1 - 1, boundsIn, changedBlocks);
                                        placeLeaves(LEAVES, worldIn, posX1 + 1, topTrunkHeight + posYLeafHeight - 2, posZ1 + 3, boundsIn, changedBlocks);
                                        placeLeaves(LEAVES, worldIn, posX1 + 1, topTrunkHeight + posYLeafHeight - 3, posZ1 + 4, boundsIn, changedBlocks);

                                        placeLeaves(LEAVES, worldIn, posX1 + 1, topTrunkHeight + posYLeafHeight - 4, posZ1 + 6, boundsIn, changedBlocks);

                                        placeLeaves(LEAVES, worldIn, posX1 - 1, topTrunkHeight + posYLeafHeight - 2, posZ1 + 2, boundsIn, changedBlocks);

                                        placeLeaves(LEAVES, worldIn, posX1 - 4, topTrunkHeight + posYLeafHeight - 2, posZ1 + 1, boundsIn, changedBlocks);
                                        placeLeaves(LEAVES, worldIn, posX1 - 4, topTrunkHeight + posYLeafHeight - 2, posZ1, boundsIn, changedBlocks);
                                        placeLeaves(LEAVES, worldIn, posX1 - 2, topTrunkHeight + posYLeafHeight - 2, posZ + 1, boundsIn, changedBlocks);

                                        placeLeaves(LEAVES, worldIn, posX1 - 5, topTrunkHeight + posYLeafHeight - 4, posZ1 + 2, boundsIn, changedBlocks);
                                        placeLeaves(LEAVES, worldIn, posX1 - 2, topTrunkHeight - posYLeafHeight, posZ1 - 1, boundsIn, changedBlocks);
                                        placeLeaves(LEAVES, worldIn, posX1 - 3, topTrunkHeight - posYLeafHeight - 2, posZ1 - 2, boundsIn, changedBlocks);

                                        placeLeaves(LEAVES, worldIn, posX1 + 4, topTrunkHeight - posYLeafHeight - 2, posZ1 - 3, boundsIn, changedBlocks);


                                    }
                                    placeLeaves(LEAVES, worldIn, posX1 + 2, topTrunkHeight + posYLeafHeight - 3, posZ1, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 + 5, topTrunkHeight + posYLeafHeight - 4, posZ1 - 2, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 + 5, topTrunkHeight + posYLeafHeight - 6, posZ1 - 2, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 + 4, topTrunkHeight + posYLeafHeight - 3, posZ1 - 1, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 + 4, topTrunkHeight + posYLeafHeight - 4, posZ1 - 1, boundsIn, changedBlocks);

                                    placeLeaves(LEAVES, worldIn, posX1 + 1, topTrunkHeight + posYLeafHeight - 4, posZ1 + 5, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 + 1, topTrunkHeight + posYLeafHeight - 6, posZ1 + 5, boundsIn, changedBlocks);

                                    placeLeaves(LEAVES, worldIn, posX1, topTrunkHeight + posYLeafHeight - 3, posZ1 + 3, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1, topTrunkHeight + posYLeafHeight - 3, posZ1 + 4, boundsIn, changedBlocks);

                                    placeLeaves(LEAVES, worldIn, posX1 - 1, topTrunkHeight + posYLeafHeight - 4, posZ1 + 3, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 - 1, topTrunkHeight + posYLeafHeight - 5, posZ1 + 3, boundsIn, changedBlocks);

                                    placeLeaves(LEAVES, worldIn, posX1 - 3, topTrunkHeight + posYLeafHeight - 3, posZ1, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 - 2, topTrunkHeight + posYLeafHeight - 3, posZ1, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 - 1, topTrunkHeight + posYLeafHeight - 5, posZ1, boundsIn, changedBlocks);

                                    placeLeaves(LEAVES, worldIn, posX1 - 6, topTrunkHeight + posYLeafHeight - 6, posZ1 + 2, boundsIn, changedBlocks);


                                    placeLeaves(LEAVES, worldIn, posX1 + 1, topTrunkHeight - 2, posZ1 - 5, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 + 1, topTrunkHeight - 1, posZ1 - 4, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 + 1, topTrunkHeight - 3, posZ1 - 4, boundsIn, changedBlocks);

                                    placeLeaves(LEAVES, worldIn, posX1 + 2, topTrunkHeight - 2, posZ1 - 3, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 + 2, topTrunkHeight, posZ1 - 3, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 + 2, topTrunkHeight - 1, posZ1 - 2, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 + 5, topTrunkHeight - 3, posZ1 - 1, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 + 5, topTrunkHeight - 1, posZ1 - 1, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 + 3, topTrunkHeight - 1, posZ1 - 1, boundsIn, changedBlocks);

                                    placeLeaves(LEAVES, worldIn, posX1 + 6, topTrunkHeight - 2, posZ1 - 2, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 + 4, topTrunkHeight - 2, posZ1 - 2, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 + 3, topTrunkHeight - 2, posZ1 - 2, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 + 1, topTrunkHeight - 2, posZ1 - 2, boundsIn, changedBlocks);


                                    placeLeaves(LEAVES, worldIn, posX1 + 1, topTrunkHeight - 2, posZ1 - 2, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 + 3, topTrunkHeight - 1, posZ1 - 2, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 + 2, topTrunkHeight, posZ1 - 2, boundsIn, changedBlocks);


                                    placeLeaves(LEAVES, worldIn, posX1 + 5, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);


                                    placeLeaves(LEAVES, worldIn, posX1 - 2, topTrunkHeight + posYLeafHeight - 5, posZ1 - 5, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 - 1, topTrunkHeight + posYLeafHeight - 4, posZ1 - 5, boundsIn, changedBlocks);

                                    placeLeaves(LEAVES, worldIn, posX1 + 1, topTrunkHeight + posYLeafHeight - 3, posZ1 + 2, boundsIn, changedBlocks);


                                    if (posYLeafHeight <= 1) {
                                        placeLeaves(LEAVES, worldIn, posX1 - 1, topTrunkHeight + posYLeafHeight - 3, posZ1 - 6, boundsIn, changedBlocks);
                                        placeLeaves(LEAVES, worldIn, posX1 + 3, topTrunkHeight + posYLeafHeight - 2, posZ1 + 1, boundsIn, changedBlocks);
                                        placeLeaves(LEAVES, worldIn, posX1 + 3, topTrunkHeight + posYLeafHeight - 2, posZ1 + 2, boundsIn, changedBlocks);
                                        placeLeaves(LEAVES, worldIn, posX1 + 2, topTrunkHeight + posYLeafHeight - 3, posZ1 + 5, boundsIn, changedBlocks);
                                        placeLeaves(LEAVES, worldIn, posX1, topTrunkHeight + posYLeafHeight - 2, posZ1 + 5, boundsIn, changedBlocks);

                                        placeLeaves(LEAVES, worldIn, posX1 - 2, topTrunkHeight + posYLeafHeight - 1, posZ1 + 2, boundsIn, changedBlocks);


                                    }
                                    placeLeaves(LEAVES, worldIn, posX1 - 2, topTrunkHeight - 2, posZ1 - 6, boundsIn, changedBlocks);

                                    placeLeaves(LEAVES, worldIn, posX1 + 4, topTrunkHeight - 1, posZ1 + 1, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 + 4, topTrunkHeight - 1, posZ1 + 2, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 + 3, topTrunkHeight, posZ1 + 2, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 + 2, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 + 2, topTrunkHeight, posZ1 + 3, boundsIn, changedBlocks);

                                    placeLeaves(LEAVES, worldIn, posX1 + 2, topTrunkHeight - 1, posZ1 + 2, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 + 2, topTrunkHeight - 1, posZ1 + 4, boundsIn, changedBlocks);

                                    placeLeaves(LEAVES, worldIn, posX1 + 2, topTrunkHeight - 2, posZ1 + 3, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 + 2, topTrunkHeight - 2, posZ1 + 1, boundsIn, changedBlocks);

                                    placeLeaves(LEAVES, worldIn, posX1 - 1, topTrunkHeight - 1, posZ1 + 4, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 - 1, topTrunkHeight - 3, posZ1 + 4, boundsIn, changedBlocks);

                                    placeLeaves(LEAVES, worldIn, posX1, topTrunkHeight - 1, posZ1 + 2, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1, topTrunkHeight - 3, posZ1 + 2, boundsIn, changedBlocks);
                                    //placeLeaves(LEAVES, worldIn, posX1 -1, topTrunkHeight + 3, posZ1 + 2, boundsIn, changedBlocks);

                                    placeLeaves(LEAVES, worldIn, posX1 - 3, topTrunkHeight + 1, posZ1 + 2, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 - 3, topTrunkHeight + 1, posZ1 + 1, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 - 3, topTrunkHeight - 1, posZ1 + 1, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 - 3, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);


                                    placeLeaves(LEAVES, worldIn, posX1 - 3, topTrunkHeight - 1, posZ1 + 2, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 - 3, topTrunkHeight - 2, posZ1 + 2, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 - 3, topTrunkHeight - 1, posZ1 + 3, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 - 2, topTrunkHeight, posZ1 + 3, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 - 2, topTrunkHeight - 2, posZ1 + 3, boundsIn, changedBlocks);

                                    placeLeaves(LEAVES, worldIn, posX1 - 5, topTrunkHeight - 1, posZ1, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 - 5, topTrunkHeight - 2, posZ1, boundsIn, changedBlocks);

                                    placeLeaves(LEAVES, worldIn, posX1 - 4, topTrunkHeight - 1, posZ1 - 1, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 - 3, topTrunkHeight - 1, posZ1 - 1, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 - 3, topTrunkHeight - 2, posZ1 - 1, boundsIn, changedBlocks);


                                    placeLeaves(LEAVES, worldIn, posX1 - 5, topTrunkHeight - 1, posZ1 + 1, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 - 6, topTrunkHeight - 2, posZ1 + 1, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 - 5, topTrunkHeight - 3, posZ1 + 1, boundsIn, changedBlocks);

                                    placeLeaves(LEAVES, worldIn, posX1 - 3, topTrunkHeight, posZ1 - 2, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 - 2, topTrunkHeight, posZ1 - 2, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 - 2, topTrunkHeight - 1, posZ1 - 2, boundsIn, changedBlocks);
                                    placeLeaves(LEAVES, worldIn, posX1 - 2, topTrunkHeight - 1, posZ1 - 3, boundsIn, changedBlocks);

                                    //placeLeaves(LEAVES, worldIn, posX1 - 3, topTrunkHeight + 5, posZ1 + 3, boundsIn, changedBlocks);


                                }
                            }
                        }
                    } else if (leavePreset == 2) {
                        int leavessquarespos = 2;
                        for (int posXLeafWidth = -leavessquarespos; posXLeafWidth <= leavessquarespos; ++posXLeafWidth) {//has to do with leaves
                            for (int posZLeafWidthL0 = -leavessquarespos; posZLeafWidthL0 <= leavessquarespos; ++posZLeafWidthL0) {

                            }
                        }
                    } else if (leavePreset == 3) {
                        int leavessquarespos = 2;
                        for (int posXLeafWidth = -leavessquarespos; posXLeafWidth <= leavessquarespos; ++posXLeafWidth) {//has to do with leaves
                            for (int posZLeafWidthL0 = -leavessquarespos; posZLeafWidthL0 <= leavessquarespos; ++posZLeafWidthL0) {

                            }
                        }
                    } else if (leavePreset == 4) {
                        int leavessquarespos = 2;
                        for (int posXLeafWidth = -leavessquarespos; posXLeafWidth <= leavessquarespos; ++posXLeafWidth) {//has to do with leaves
                            for (int posZLeafWidthL0 = -leavessquarespos; posZLeafWidthL0 <= leavessquarespos; ++posZLeafWidthL0) {

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
            int distance = 1;

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