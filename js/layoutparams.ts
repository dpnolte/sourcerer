import { GridlayoutTypes } from './gridlayout';
import { LeanbackTypes } from './leanback';
import { ViewpagerTypes } from './viewpager';
import { SlidingpanelayoutTypes } from './slidingpanelayout';
import { WearTypes } from './wear';
import { RecyclerviewTypes } from './recyclerview';
import { ComponentsMaterialTypes } from './componentsmaterial';
import { DrawerlayoutTypes } from './drawerlayout';
import { CoordinatorlayoutTypes } from './coordinatorlayout';
import { AppcompatTypes } from './appcompat';
import { MainTypes } from './main';
export type LayoutParamAttributes = {}
    | MainTypes.TableLayoutLayoutParamsAttributes
    | MainTypes.ActionMenuViewLayoutParamsAttributes
    | MainTypes.RelativeLayoutLayoutParamsAttributes
    | MainTypes.AbsListViewLayoutParamsAttributes
    | MainTypes.FrameLayoutLayoutParamsAttributes
    | MainTypes.RadioGroupLayoutParamsAttributes
    | MainTypes.TableRowLayoutParamsAttributes
    | MainTypes.LinearLayoutLayoutParamsAttributes
    | MainTypes.GridLayoutLayoutParamsAttributes
    | MainTypes.ViewGroupLayoutParamsAttributes
    | MainTypes.ToolbarLayoutParamsAttributes
    | MainTypes.ViewGroupMarginLayoutParamsAttributes
    | AppcompatTypes.LinearLayoutCompatLayoutParamsAttributes
    | AppcompatTypes.ActionMenuViewLayoutParamsAttributes
    | AppcompatTypes.ToolbarLayoutParamsAttributes
    | CoordinatorlayoutTypes.CoordinatorLayoutLayoutParamsAttributes
    | DrawerlayoutTypes.DrawerLayoutLayoutParamsAttributes
    | ComponentsMaterialTypes.AppBarLayoutLayoutParamsAttributes
    | ComponentsMaterialTypes.CollapsingToolbarLayoutLayoutParamsAttributes
    | ComponentsMaterialTypes.ChipGroupLayoutParamsAttributes
    | RecyclerviewTypes.GridLayoutManagerLayoutParamsAttributes
    | RecyclerviewTypes.RecyclerViewLayoutParamsAttributes
    | RecyclerviewTypes.StaggeredGridLayoutManagerLayoutParamsAttributes
    | WearTypes.BoxInsetLayoutLayoutParamsAttributes
    | SlidingpanelayoutTypes.SlidingPaneLayoutLayoutParamsAttributes
    | ViewpagerTypes.ViewPagerLayoutParamsAttributes
    | LeanbackTypes.BaseCardViewLayoutParamsAttributes
    | GridlayoutTypes.GridLayoutLayoutParamsAttributes;
