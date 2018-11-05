/* generated @ 2018-11-05T13:36:00.921 */
import "./base.elements";
import "./generated-core.attributes";
, import "./generated.attributes";
declare module "LayoutTypes" {
  interface NormalizedNestedScrollViewElement<
    LayoutAttributes={}
  > extends NormalizedViewGroupBase<NestedScrollViewAttributes, LayoutAttributes> {
    type: "nestedScrollView";
  }
  interface DenormalizedNestedScrollViewElement<
    LayoutAttributes={},
    ChildrenViewAttributes={}
  > extends DenormalizedViewGroupBase<NestedScrollViewAttributes, LayoutAttributes, ChildrenViewAttributes, ViewGroupLayoutParamsAttributes> {
    type: "nestedScrollView";
  }
  interface NormalizedContentLoadingProgressBarElement<LayoutAttributes={}> extends ElementBase<ContentLoadingProgressBarAttributes, LayoutAttributes> {
    type: "contentLoadingProgressBar";
  }
  interface DenormalizedContentLoadingProgressBarElement<LayoutAttributes={}> extends ElementBase<ContentLoadingProgressBarAttributes, LayoutAttributes> {
    type: "contentLoadingProgressBar";
  }}
