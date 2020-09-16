package voronoiaoc.byg.common.world.feature.features.overworld.trees.enchanted.green;

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
import voronoiaoc.byg.core.byglists.BYGBlockList;

import java.util.Random;
import java.util.Set;

public class GreenEnchantedTree3 extends BYGAbstractTreeFeature<BYGTreeFeatureConfig> {

    public GreenEnchantedTree3(Codec<BYGTreeFeatureConfig> configIn) {
        super(configIn);
    }

    protected boolean place(Set<BlockPos> changedBlocks, ISeedReader worldIn, Random rand, BlockPos pos, MutableBoundingBox boundsIn, boolean isSapling, BYGTreeFeatureConfig config) {
        BlockState LOG = config.getTrunkProvider().getBlockState(rand, pos);
        BlockState LEAVES = config.getLeavesProvider().getBlockState(rand, pos);
        int randTreeHeight = 22 + rand.nextInt(5);
        BlockPos.Mutable mainmutable = new BlockPos.Mutable().setPos(pos);

        if (pos.getY() + randTreeHeight + 1 < worldIn.getHeight()) {

            if (!isDesiredGroundwDirtTag(worldIn, pos.down(), Blocks.GRASS_BLOCK)) {
                return false;
            } else if (!this.isAnotherTreeNearby(worldIn, pos, randTreeHeight, 0, isSapling)) {
                return false;
            } else if (!this.doesSaplingHaveSpaceToGrow(worldIn, pos, randTreeHeight, 5, 5, 5, isSapling)) {
                return false;
            } else {
                for (int buildTrunk = 0; buildTrunk <= randTreeHeight; buildTrunk++) {
                    placeLog(LOG, changedBlocks, worldIn, mainmutable, boundsIn);

                    mainmutable.move(Direction.UP);
                }
                mainmutable.setPos(pos);

                this.treeBranch(changedBlocks, worldIn, mainmutable.add(3, randTreeHeight - 14, -1), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 14, 0), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 14, 0), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 13, -3), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 13, -2), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 13, -1), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 12, 0), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(-3, randTreeHeight - 12, 1), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 12, 1), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 12, 1), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 12, 2), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 12, 3), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 9, -3), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 9, -2), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 9, -1), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 9, 0), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 9, 0), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 9, 1), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(3, randTreeHeight - 9, 1), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 9, 2), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 9, 3), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 6, -1), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 6, 0), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 5, 0), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 5, 1), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 5, 1), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 5, 2), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 4, -2), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 4, -1), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 2, -1), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 2, 1), boundsIn);
                this.treeBranch(changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 2, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 15, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(3, randTreeHeight - 15, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 15, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 15, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 15, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 15, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 14, -3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(3, randTreeHeight - 14, -3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 14, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 14, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 14, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(3, randTreeHeight - 14, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(4, randTreeHeight - 14, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 14, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 14, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 14, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(4, randTreeHeight - 14, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(5, randTreeHeight - 14, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 14, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(3, randTreeHeight - 14, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(4, randTreeHeight - 14, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 14, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 14, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 14, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(3, randTreeHeight - 14, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 14, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 14, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 13, -5), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 13, -4), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 13, -4), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 13, -4), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-3, randTreeHeight - 13, -3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 13, -3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 13, -3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 13, -3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 13, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 13, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 13, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 13, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 13, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 13, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 13, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 13, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(3, randTreeHeight - 13, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 13, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 13, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 13, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-3, randTreeHeight - 13, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 13, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 13, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 13, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 13, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 13, 3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 12, -3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 12, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 12, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 12, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-3, randTreeHeight - 12, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 12, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 12, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 12, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 12, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-4, randTreeHeight - 12, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-3, randTreeHeight - 12, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 12, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 12, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 12, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-5, randTreeHeight - 12, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-4, randTreeHeight - 12, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 12, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 12, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 12, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(3, randTreeHeight - 12, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-4, randTreeHeight - 12, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-3, randTreeHeight - 12, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 12, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 12, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 12, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 12, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(3, randTreeHeight - 12, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-3, randTreeHeight - 12, 3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 12, 3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 12, 3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 12, 3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(3, randTreeHeight - 12, 3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(4, randTreeHeight - 12, 3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 12, 4), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 12, 4), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(3, randTreeHeight - 12, 4), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 12, 5), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 11, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 11, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 11, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-3, randTreeHeight - 11, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 11, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 11, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 11, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 11, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 11, 3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 10, -3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 10, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 10, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 10, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 10, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 10, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 10, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 10, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(3, randTreeHeight - 10, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 10, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 10, 3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 9, -5), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-3, randTreeHeight - 9, -4), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 9, -4), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 9, -4), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-4, randTreeHeight - 9, -3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-3, randTreeHeight - 9, -3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 9, -3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 9, -3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-4, randTreeHeight - 9, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-3, randTreeHeight - 9, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 9, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 9, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 9, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 9, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-3, randTreeHeight - 9, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 9, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 9, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 9, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 9, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(3, randTreeHeight - 9, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 9, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 9, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(3, randTreeHeight - 9, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(4, randTreeHeight - 9, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 9, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 9, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 9, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 9, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(4, randTreeHeight - 9, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(5, randTreeHeight - 9, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-3, randTreeHeight - 9, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 9, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 9, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 9, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 9, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(3, randTreeHeight - 9, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(4, randTreeHeight - 9, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-3, randTreeHeight - 9, 3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 9, 3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 9, 3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 9, 3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(3, randTreeHeight - 9, 3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 9, 4), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 9, 4), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 9, 4), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 9, 5), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 8, -3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 8, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 8, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 8, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 8, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 8, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 8, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 8, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(3, randTreeHeight - 8, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 8, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 8, 3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 7, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 7, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 7, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 7, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 7, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 6, -3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 6, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 6, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(3, randTreeHeight - 6, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 6, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 6, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(3, randTreeHeight - 6, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(4, randTreeHeight - 6, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 6, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 6, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(3, randTreeHeight - 6, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 6, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 6, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 6, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 6, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 6, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 5, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 5, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 5, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 5, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 5, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 5, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-3, randTreeHeight - 5, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 5, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 5, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-4, randTreeHeight - 5, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-3, randTreeHeight - 5, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 5, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 5, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 5, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-3, randTreeHeight - 5, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 5, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 5, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 5, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 5, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(3, randTreeHeight - 5, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 5, 3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 5, 3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 5, 3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 5, 3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 5, 4), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 4, -4), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 4, -3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 4, -3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 4, -3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 4, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 4, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 4, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(3, randTreeHeight - 4, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 4, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 4, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 4, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 4, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 4, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 4, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 4, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 4, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 4, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 3, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 3, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 3, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 3, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 2, -3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 2, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 2, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 2, -2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 2, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 2, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 2, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 2, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 2, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 2, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 2, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-3, randTreeHeight - 2, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 2, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 2, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 2, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(3, randTreeHeight - 2, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-2, randTreeHeight - 2, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 2, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 2, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 2, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(2, randTreeHeight - 2, 2), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 2, 3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 2, 3), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 1, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 1, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 1, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight - 1, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight - 1, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight - 1, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight, -1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(-1, randTreeHeight, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(1, randTreeHeight, 0), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight, 1), boundsIn);
                placeLeaves(LEAVES, changedBlocks, worldIn, mainmutable.add(0, randTreeHeight + 1, 0), boundsIn);
            }
        }
        return true;
    }


    private void treeLog(Set<BlockPos> setlogblock, ISeedReader reader, BlockPos pos, MutableBoundingBox boundingBox) {
        if (canLogPlaceHere(reader, pos)) {
            this.setFinalBlockState(setlogblock, reader, pos, BYGBlockList.GREEN_ENCHANTED_LOG.getDefaultState(), boundingBox);
        }
    }


    private void treeBranch(Set<BlockPos> setlogblock, ISeedReader reader, BlockPos pos, MutableBoundingBox boundingBox) {
        if (canLogPlaceHere(reader, pos)) {
            this.setFinalBlockState(setlogblock, reader, pos, BYGBlockList.GREEN_ENCHANTED_LOG.getDefaultState(), boundingBox);
        }
    }


    private void leafs(Set<BlockPos> blockPos, ISeedReader reader, BlockPos pos, MutableBoundingBox boundingBox) {
        BlockPos.Mutable blockpos = new BlockPos.Mutable().setPos(pos);
        if (isAir(reader, blockpos)) {
            this.setFinalBlockState(blockPos, reader, blockpos, BYGBlockList.GREEN_ENCHANTED_LEAVES.getDefaultState(), boundingBox);
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