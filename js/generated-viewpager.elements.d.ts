/* generated @ 2018-11-05T13:37:33.633 */
import "./base.elements";
import "./generated-viewpager.attributes";
, import "./generated.attributes";
declare module "LayoutTypes" {
  interface NormalizedPagerTabStripElement<
    LayoutAttributes={}
  > extends NormalizedViewGroupBase<PagerTabStripAttributes, LayoutAttributes> {
    type: "pagerTabStrip";
  }
  interface DenormalizedPagerTabStripElement<
    LayoutAttributes={},
    ChildrenViewAttributes={}
  > extends DenormalizedViewGroupBase<PagerTabStripAttributes, LayoutAttributes, ChildrenViewAttributes, ViewGroupLayoutParamsAttributes> {
    type: "pagerTabStrip";
  }
  interface NormalizedPagerTitleStripElement<
    LayoutAttributes={}
  > extends NormalizedViewGroupBase<PagerTitleStripAttributes, LayoutAttributes> {
    type: "pagerTitleStrip";
  }
  interface DenormalizedPagerTitleStripElement<
    LayoutAttributes={},
    ChildrenViewAttributes={}
  > extends DenormalizedViewGroupBase<PagerTitleStripAttributes, LayoutAttributes, ChildrenViewAttributes, ViewGroupLayoutParamsAttributes> {
    type: "pagerTitleStrip";
  }
  interface NormalizedViewPagerElement<
    LayoutAttributes={}
  > extends NormalizedViewGroupBase<ViewPagerAttributes, LayoutAttributes> {
    type: "viewPager";
  }
  interface DenormalizedViewPagerElement<
    LayoutAttributes={},
    ChildrenViewAttributes={}
  > extends DenormalizedViewGroupBase<ViewPagerAttributes, LayoutAttributes, ChildrenViewAttributes, ViewPagerLayoutParamsAttributes> {
    type: "viewPager";
  }}
