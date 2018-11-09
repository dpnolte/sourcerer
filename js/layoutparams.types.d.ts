/// <reference path='./gridlayout.types.d.ts' />
/// <reference path='./leanback.types.d.ts' />
/// <reference path='./slidingpanelayout.types.d.ts' />
/// <reference path='./viewpager.types.d.ts' />
/// <reference path='./wear.types.d.ts' />
/// <reference path='./recyclerview.types.d.ts' />
/// <reference path='./componentsmaterial.types.d.ts' />
/// <reference path='./drawerlayout.types.d.ts' />
/// <reference path='./coordinatorlayout.types.d.ts' />
/// <reference path='./appcompat.types.d.ts' />
/// <reference path='./main.types.d.ts' />
declare namespace LayoutParamsTypes {
  type LayoutParamAttributes = {}
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
    | ViewpagerTypes.ViewPagerLayoutParamsAttributes
    | SlidingpanelayoutTypes.SlidingPaneLayoutLayoutParamsAttributes
    | LeanbackTypes.BaseCardViewLayoutParamsAttributes
    | GridlayoutTypes.GridLayoutLayoutParamsAttributes;
}
