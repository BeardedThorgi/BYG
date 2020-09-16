package voronoiaoc.byg.common.world.feature.features.overworld.trees.deciduous;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import voronoiaoc.byg.common.world.feature.config.BYGTreeFeatureConfig;
import voronoiaoc.byg.common.world.feature.features.overworld.trees.util.BYGAbstractTreeFeature;

import java.util.Random;
import java.util.Set;

//THIS FEATURE MUST BE REGISTERED & ADDED TO A BIOME!
public class SeasonalDeciduousShrubs extends BYGAbstractTreeFeature<BYGTreeFeatureConfig> {
    //Blocks used for the tree.
    //private static final BlockState LOG = Blocks.OAK_LOG.getDefaultState();
    //private static final BlockState LEAVES = Blocks.OAK_LEAVES.getDefaultState();
    //private static final BlockState LEAVES2 = BYGBlockList.RED_OAK_LEAVES.getDefaultState();
    //private static final BlockState LEAVES3 = BYGBlockList.BROWN_OAK_LEAVES.getDefaultState();
    //private static final BlockState LEAVES4 = BYGBlockList.ORANGE_OAK_LEAVES.getDefaultState();
    //private static final BlockState LEAVES5 = BYGBlockList.YELLOW_BIRCH_LEAVES.getDefaultState();
    private static final BlockState BEENEST = Blocks.BEE_NEST.getDefaultState();

    public SeasonalDeciduousShrubs(Codec<BYGTreeFeatureConfig> configIn) {
        super(configIn);
    }

    protected static boolean canTreeReplace(IWorldGenerationBaseReader genBaseReader, BlockPos blockPos) {
        return canLogPlaceHere(
                genBaseReader, blockPos
        );
    }

    @Override
    protected boolean place(Set<BlockPos> changedBlocks, ISeedReader worldIn, Random rand, BlockPos pos, MutableBoundingBox boundsIn, boolean isSapling, BYGTreeFeatureConfig config) {
        BlockState LOG = config.getTrunkProvider().getBlockState(rand, pos);
        BlockState LEAVES = config.getLeavesProvider().getBlockState(rand, pos);//This sets heights for trees. Rand.nextint allows for tree height randomization. The final int value sets the minimum for tree Height.
        int randTreeHeight = 1;
        //Positions
        int posX = pos.getX();
        int posY = pos.getY();
        int posZ = pos.getZ();
        if (posY >= 1 && posY + randTreeHeight + 1 < worldIn.getHeight()) {

            if (!isDesiredGroundwDirtTag(worldIn, pos.down(), Blocks.GRASS_BLOCK)) {
                return false;
            } else if (!this.isAnotherTreeNearby(worldIn, pos, randTreeHeight, 0, isSapling)) {
                return false;
            } else if (!this.doesSaplingHaveSpaceToGrow(worldIn, pos, randTreeHeight, 5, 5, 5, isSapling)) {
                return false;
            } else {
                //Places dirt under logs where/when necessary.

//Uncommenting this will allow for a 2x2 dirt patch under the tree.
                /*this.setGroundBlockAt(worldIn, blockpos.east(), pos, Blocks.DIRT.getDefaultState());

                this.setGroundBlockAt(worldIn, blockpos.south(), pos, Blocks.DIRT.getDefaultState());

                this.setGroundBlockAt(worldIn, blockpos.south().east(), pos, Blocks.DIRT.getDefaultState());
*/
                //Uncommenting this will allow for a 3x3 dirt patch under the tree.
                /*this.setGroundBlockAt(worldIn, blockpos.west(), pos, Blocks.DIRT.getDefaultState());

                this.setGroundBlockAt(worldIn, blockpos.south().west(), pos, Blocks.DIRT.getDefaultState());

                this.setGroundBlockAt(worldIn, blockpos.north(), pos, Blocks.DIRT.getDefaultState());

                this.setGroundBlockAt(worldIn, blockpos.north().east(), pos, Blocks.DIRT.getDefaultState());

                this.setGroundBlockAt(worldIn, blockpos.north().west(), pos, Blocks.DIRT.getDefaultState());
*/


                Direction direction = Direction.Plane.HORIZONTAL.random(rand);
                int randTreeHeight2 = randTreeHeight - rand.nextInt(1);
                int posY1 = 2 - rand.nextInt(1);
                int posX1 = posX;
                int posZ1 = posZ;
                int topTrunkHeight = posY + randTreeHeight - 1;

                //Raising the 'groundUpLogRemover'  will remove all log blocks from the ground up no matter how thick the trunk is based on the value given. 5 would destroy all trunks from 5 up off the ground.
                for (int groundUpLogRemover = 0; groundUpLogRemover < randTreeHeight; ++groundUpLogRemover) {
                    if (groundUpLogRemover >= randTreeHeight2 && posY1 < 0) {
                        posX1 += direction.getXOffset();
                        posZ1 += direction.getZOffset();
                        ++posY1;
                    }
                    //This Int is responsible for the Y coordinate of the trunk BlockPos'.
                    int logplacer = posY + groundUpLogRemover;
                    BlockPos blockpos1 = new BlockPos(posX1, logplacer, posZ1);
                    //These BlockPos' allow you to make trunks thicker than 2x2,
                    BlockPos blockposwest1 = new BlockPos(posX1 - 1, logplacer, posZ1);
                    BlockPos blockposnorth1 = new BlockPos(posX1, logplacer, posZ1 - 1);
                    BlockPos blockposnorthwest1 = new BlockPos(posX1 - 1, logplacer, posZ1 - 1);


                    //Sets Logs
                    if (isAir(worldIn, blockpos1)) {
                        placeLog(LOG, changedBlocks, worldIn, blockpos1, boundsIn);
                    }
                }
                //This allows a random rotation between 3 differently leave Presets in the same class. Optimizes Performance instead of the loading of several classes.
                int leavePreset = rand.nextInt(1) + 1;
                {
                    if (leavePreset == 1) {
                        //This randomizer allows you to have randomly generating sized leave widths(X & Z). You can remove the randomizer and set your own value instead.
                        int leavessquarespos = rand.nextInt(1) + 1;
                        //This loads leaves in squares. Manually placing the squares can allow you to load in perfect squares.
                        for (int posXLeafWidth = -leavessquarespos; posXLeafWidth <= leavessquarespos; ++posXLeafWidth) {//has to do with leaves
                            for (int posZLeafWidthL0 = -leavessquarespos; posZLeafWidthL0 <= leavessquarespos; ++posZLeafWidthL0) {
                                //int leaveheight = 1;//0 lines it up with top log
                                int leafcolor = rand.nextInt(5) + 1;
                                if (leafcolor == 1) {
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
                                } else if (leafcolor == 2) {
                                    this.leafs2(worldIn, posX1 + posXLeafWidth, topTrunkHeight, posZ1 + posZLeafWidthL0, boundsIn, changedBlocks);

                                    this.leafs2(worldIn, posX1 + 1, topTrunkHeight, posZ1, boundsIn, changedBlocks);
                                    this.leafs2(worldIn, posX1 + 2, topTrunkHeight, posZ1, boundsIn, changedBlocks);
                                    this.leafs2(worldIn, posX1 - 1, topTrunkHeight, posZ1, boundsIn, changedBlocks);
                                    this.leafs2(worldIn, posX1 - 2, topTrunkHeight, posZ1, boundsIn, changedBlocks);

                                    this.leafs2(worldIn, posX1, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                    this.leafs2(worldIn, posX1, topTrunkHeight, posZ1 - 2, boundsIn, changedBlocks);
                                    this.leafs2(worldIn, posX1, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                    this.leafs2(worldIn, posX1, topTrunkHeight, posZ1 + 2, boundsIn, changedBlocks);

                                    this.leafs2(worldIn, posX1 + 1, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                    this.leafs2(worldIn, posX1 + 1, topTrunkHeight, posZ1 + 2, boundsIn, changedBlocks);
                                    this.leafs2(worldIn, posX1 + 1, topTrunkHeight, posZ1 - 2, boundsIn, changedBlocks);
                                    this.leafs2(worldIn, posX1 - 1, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                    this.leafs2(worldIn, posX1 - 1, topTrunkHeight, posZ1 + 2, boundsIn, changedBlocks);
                                    this.leafs2(worldIn, posX1 - 1, topTrunkHeight, posZ1 - 2, boundsIn, changedBlocks);
                                    this.leafs2(worldIn, posX1 - 1, topTrunkHeight, posZ1 - 2, boundsIn, changedBlocks);
                                    this.leafs2(worldIn, posX1 + 2, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                    this.leafs2(worldIn, posX1 + 2, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                    this.leafs2(worldIn, posX1 - 2, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                    this.leafs2(worldIn, posX1 - 2, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);

                                    //Y+1
                                    this.leafs2(worldIn, posX1 + 1, topTrunkHeight + 1, posZ1, boundsIn, changedBlocks);
                                    this.leafs2(worldIn, posX1 - 1, topTrunkHeight + 1, posZ1, boundsIn, changedBlocks);
                                    this.leafs2(worldIn, posX1, topTrunkHeight + 1, posZ1 + 1, boundsIn, changedBlocks);
                                    this.leafs2(worldIn, posX1, topTrunkHeight + 1, posZ1 - 1, boundsIn, changedBlocks);
                                    this.leafs2(worldIn, posX1, topTrunkHeight + 1, posZ1, boundsIn, changedBlocks);

                                    this.leafs2(worldIn, posX1 + 1, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                    this.leafs2(worldIn, posX1 + 1, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                    this.leafs2(worldIn, posX1 - 1, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                    this.leafs2(worldIn, posX1 - 1, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                } else if (leafcolor == 3) {
                                    this.leafs3(worldIn, posX1 + posXLeafWidth, topTrunkHeight, posZ1 + posZLeafWidthL0, boundsIn, changedBlocks);

                                    this.leafs3(worldIn, posX1 + 1, topTrunkHeight, posZ1, boundsIn, changedBlocks);
                                    this.leafs3(worldIn, posX1 + 2, topTrunkHeight, posZ1, boundsIn, changedBlocks);
                                    this.leafs3(worldIn, posX1 - 1, topTrunkHeight, posZ1, boundsIn, changedBlocks);
                                    this.leafs3(worldIn, posX1 - 2, topTrunkHeight, posZ1, boundsIn, changedBlocks);

                                    this.leafs3(worldIn, posX1, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                    this.leafs3(worldIn, posX1, topTrunkHeight, posZ1 - 2, boundsIn, changedBlocks);
                                    this.leafs3(worldIn, posX1, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                    this.leafs3(worldIn, posX1, topTrunkHeight, posZ1 + 2, boundsIn, changedBlocks);

                                    this.leafs3(worldIn, posX1 + 1, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                    this.leafs3(worldIn, posX1 + 1, topTrunkHeight, posZ1 + 2, boundsIn, changedBlocks);
                                    this.leafs3(worldIn, posX1 + 1, topTrunkHeight, posZ1 - 2, boundsIn, changedBlocks);
                                    this.leafs3(worldIn, posX1 - 1, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                    this.leafs3(worldIn, posX1 - 1, topTrunkHeight, posZ1 + 2, boundsIn, changedBlocks);
                                    this.leafs3(worldIn, posX1 - 1, topTrunkHeight, posZ1 - 2, boundsIn, changedBlocks);
                                    this.leafs3(worldIn, posX1 - 1, topTrunkHeight, posZ1 - 2, boundsIn, changedBlocks);
                                    this.leafs3(worldIn, posX1 + 2, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                    this.leafs3(worldIn, posX1 + 2, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                    this.leafs3(worldIn, posX1 - 2, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                    this.leafs3(worldIn, posX1 - 2, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);


                                    //Y+1
                                    this.leafs3(worldIn, posX1 + 1, topTrunkHeight + 1, posZ1, boundsIn, changedBlocks);
                                    this.leafs3(worldIn, posX1 - 1, topTrunkHeight + 1, posZ1, boundsIn, changedBlocks);
                                    this.leafs3(worldIn, posX1, topTrunkHeight + 1, posZ1 + 1, boundsIn, changedBlocks);
                                    this.leafs3(worldIn, posX1, topTrunkHeight + 1, posZ1 - 1, boundsIn, changedBlocks);
                                    this.leafs3(worldIn, posX1, topTrunkHeight + 1, posZ1, boundsIn, changedBlocks);

                                    this.leafs3(worldIn, posX1 + 1, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                    this.leafs3(worldIn, posX1 + 1, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                    this.leafs3(worldIn, posX1 - 1, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                    this.leafs3(worldIn, posX1 - 1, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                } else if (leafcolor == 4) {
                                    this.leafs4(worldIn, posX1 + posXLeafWidth, topTrunkHeight, posZ1 + posZLeafWidthL0, boundsIn, changedBlocks);

                                    this.leafs4(worldIn, posX1 + 1, topTrunkHeight, posZ1, boundsIn, changedBlocks);
                                    this.leafs4(worldIn, posX1 + 2, topTrunkHeight, posZ1, boundsIn, changedBlocks);
                                    this.leafs4(worldIn, posX1 - 1, topTrunkHeight, posZ1, boundsIn, changedBlocks);
                                    this.leafs4(worldIn, posX1 - 2, topTrunkHeight, posZ1, boundsIn, changedBlocks);

                                    this.leafs4(worldIn, posX1, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                    this.leafs4(worldIn, posX1, topTrunkHeight, posZ1 - 2, boundsIn, changedBlocks);
                                    this.leafs4(worldIn, posX1, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                    this.leafs4(worldIn, posX1, topTrunkHeight, posZ1 + 2, boundsIn, changedBlocks);

                                    this.leafs4(worldIn, posX1 + 1, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                    this.leafs4(worldIn, posX1 + 1, topTrunkHeight, posZ1 + 2, boundsIn, changedBlocks);
                                    this.leafs4(worldIn, posX1 + 1, topTrunkHeight, posZ1 - 2, boundsIn, changedBlocks);
                                    this.leafs4(worldIn, posX1 - 1, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                    this.leafs4(worldIn, posX1 - 1, topTrunkHeight, posZ1 + 2, boundsIn, changedBlocks);
                                    this.leafs4(worldIn, posX1 - 1, topTrunkHeight, posZ1 - 2, boundsIn, changedBlocks);
                                    this.leafs4(worldIn, posX1 - 1, topTrunkHeight, posZ1 - 2, boundsIn, changedBlocks);
                                    this.leafs4(worldIn, posX1 + 2, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                    this.leafs4(worldIn, posX1 + 2, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                    this.leafs4(worldIn, posX1 - 2, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                    this.leafs4(worldIn, posX1 - 2, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);

                                    //Y+1
                                    this.leafs4(worldIn, posX1 + 1, topTrunkHeight + 1, posZ1, boundsIn, changedBlocks);
                                    this.leafs4(worldIn, posX1 - 1, topTrunkHeight + 1, posZ1, boundsIn, changedBlocks);
                                    this.leafs4(worldIn, posX1, topTrunkHeight + 1, posZ1 + 1, boundsIn, changedBlocks);
                                    this.leafs4(worldIn, posX1, topTrunkHeight + 1, posZ1 - 1, boundsIn, changedBlocks);
                                    this.leafs4(worldIn, posX1, topTrunkHeight + 1, posZ1, boundsIn, changedBlocks);

                                    this.leafs4(worldIn, posX1 + 1, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                    this.leafs4(worldIn, posX1 + 1, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                    this.leafs4(worldIn, posX1 - 1, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                    this.leafs4(worldIn, posX1 - 1, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                } else if (leafcolor == 5) {
                                    //this.leafs5(worldIn, posX1 + posXLeafWidth, topTrunkHeight, posZ1 + posZLeafWidthL0, boundsIn, changedBlocks);

                                    this.leafs5(worldIn, posX1 + 1, topTrunkHeight, posZ1, boundsIn, changedBlocks);
                                    this.leafs5(worldIn, posX1 + 2, topTrunkHeight, posZ1, boundsIn, changedBlocks);
                                    this.leafs5(worldIn, posX1 - 1, topTrunkHeight, posZ1, boundsIn, changedBlocks);
                                    this.leafs5(worldIn, posX1 - 2, topTrunkHeight, posZ1, boundsIn, changedBlocks);

                                    this.leafs5(worldIn, posX1, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                    this.leafs5(worldIn, posX1, topTrunkHeight, posZ1 - 2, boundsIn, changedBlocks);
                                    this.leafs5(worldIn, posX1, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                    this.leafs5(worldIn, posX1, topTrunkHeight, posZ1 + 2, boundsIn, changedBlocks);

                                    this.leafs5(worldIn, posX1 + 1, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                    this.leafs5(worldIn, posX1 + 1, topTrunkHeight, posZ1 + 2, boundsIn, changedBlocks);
                                    this.leafs5(worldIn, posX1 + 1, topTrunkHeight, posZ1 - 2, boundsIn, changedBlocks);
                                    this.leafs5(worldIn, posX1 - 1, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                    this.leafs5(worldIn, posX1 - 1, topTrunkHeight, posZ1 + 2, boundsIn, changedBlocks);
                                    this.leafs5(worldIn, posX1 - 1, topTrunkHeight, posZ1 - 2, boundsIn, changedBlocks);
                                    this.leafs5(worldIn, posX1 - 1, topTrunkHeight, posZ1 - 2, boundsIn, changedBlocks);
                                    this.leafs5(worldIn, posX1 + 2, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                    this.leafs5(worldIn, posX1 + 2, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                    this.leafs5(worldIn, posX1 - 2, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                    this.leafs5(worldIn, posX1 - 2, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);

                                    //Y+1
                                    this.leafs5(worldIn, posX1 + 1, topTrunkHeight + 1, posZ1, boundsIn, changedBlocks);
                                    this.leafs5(worldIn, posX1 - 1, topTrunkHeight + 1, posZ1, boundsIn, changedBlocks);
                                    this.leafs5(worldIn, posX1, topTrunkHeight + 1, posZ1 + 1, boundsIn, changedBlocks);
                                    this.leafs5(worldIn, posX1, topTrunkHeight + 1, posZ1 - 1, boundsIn, changedBlocks);
                                    this.leafs5(worldIn, posX1, topTrunkHeight + 1, posZ1, boundsIn, changedBlocks);

                                    this.leafs5(worldIn, posX1 + 1, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                    this.leafs5(worldIn, posX1 + 1, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                    this.leafs5(worldIn, posX1 - 1, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                                    this.leafs5(worldIn, posX1 - 1, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                                }
                            }
                        }
                    } else if (leavePreset == 2) {
                        int leavessquarespos = rand.nextInt(1) + 1;
                        for (int posXLeafWidth = -leavessquarespos; posXLeafWidth <= leavessquarespos; ++posXLeafWidth) {//has to do with leaves
                            for (int posZLeafWidthL0 = -leavessquarespos; posZLeafWidthL0 <= leavessquarespos; ++posZLeafWidthL0) {
                                //int leaveheight = 0;// '0' lines it up with top log. This shouldn't be necessary.

                                //Places your square leaves.
                                //placeLeaves(LEAVES, worldIn, posX1 + posXLeafWidth, topTrunkHeight + 1, posZ1 + posZLeafWidthL0, boundsIn, changedBlocks);


                                //Places your individual leave blocks by hand. 1 at a time.
                                //placeLeaves(LEAVES, worldIn, posX1 + 2, topTrunkHeight - 1, posZ1, boundsIn, changedBlocks);
                            }
                        }
                    }
                    //This is the BYGTree Maker in action. This would be tree leave Preset 3. I'd suggest commenting this out! SHOULD BE USED ONLY ON 1x1 TREE TRUNKS!
                    else if (leavePreset == 3) {
                        int leavessquarespos = rand.nextInt(1) + 1;
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
            int distance = 2;

            for (int xOffset = -distance; xOffset <= distance; ++xOffset) {
                for (int zOffset = -distance; zOffset <= distance; ++zOffset) {
                    if (!canTreeReplace(reader, pos.setPos(x + xOffset, y + yOffset, z + zOffset))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    private void treelog(Set<BlockPos> setlogblock, IWorldGenerationBaseReader reader, BlockPos pos, MutableBoundingBox boundingBox) {
        if (canTreeReplace(reader, pos)) {
            this.setFinalBlockState(setlogblock, (IWorldWriter) reader, pos, LOG, boundingBox);
        }

    }


    private void leafs(IWorldGenerationBaseReader reader, int x, int y, int z, MutableBoundingBox boundingBox, Set<BlockPos> blockPos) {
        BlockPos blockpos = new BlockPos(x, y, z);
        if (isAir(reader, blockpos)) {
            this.setFinalBlockState(blockPos, (IWorldWriter) reader, blockpos, LEAVES, boundingBox);
        }

    }

    private void leafs2(IWorldGenerationBaseReader reader, int x, int y, int z, MutableBoundingBox boundingBox, Set<BlockPos> blockPos) {
        BlockPos blockpos = new BlockPos(x, y, z);
        if (isAir(reader, blockpos)) {
            this.setFinalBlockState(blockPos, (IWorldWriter) reader, blockpos, LEAVES2, boundingBox);
        }

    }

    private void leafs3(IWorldGenerationBaseReader reader, int x, int y, int z, MutableBoundingBox boundingBox, Set<BlockPos> blockPos) {
        BlockPos blockpos = new BlockPos(x, y, z);
        if (isAir(reader, blockpos)) {
            this.setFinalBlockState(blockPos, (IWorldWriter) reader, blockpos, LEAVES3, boundingBox);
        }

    }

    private void leafs4(IWorldGenerationBaseReader reader, int x, int y, int z, MutableBoundingBox boundingBox, Set<BlockPos> blockPos) {
        BlockPos blockpos = new BlockPos(x, y, z);
        if (isAir(reader, blockpos)) {
            this.setFinalBlockState(blockPos, (IWorldWriter) reader, blockpos, LEAVES4, boundingBox);
        }

    }

    private void leafs5(IWorldGenerationBaseReader reader, int x, int y, int z, MutableBoundingBox boundingBox, Set<BlockPos> blockPos) {
        BlockPos blockpos = new BlockPos(x, y, z);
        if (isAir(reader, blockpos)) {
            this.setFinalBlockState(blockPos, (IWorldWriter) reader, blockpos, LEAVES5, boundingBox);
        }

    }
}