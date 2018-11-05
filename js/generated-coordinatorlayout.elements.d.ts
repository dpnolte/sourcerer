/* generated @ 2018-11-05T13:35:58.538 */
import "./base.elements";
import "./generated-coordinatorlayout.attributes";
declare module "LayoutTypes" {
  interface NormalizedCoordinatorLayoutElement<
    LayoutAttributes={}
  > extends NormalizedViewGroupBase<CoordinatorLayoutAttributes, LayoutAttributes> {
    type: "coordinatorLayout";
  }
  interface DenormalizedCoordinatorLayoutElement<
    LayoutAttributes={},
    ChildrenViewAttributes={}
  > extends DenormalizedViewGroupBase<CoordinatorLayoutAttributes, LayoutAttributes, ChildrenViewAttributes, CoordinatorLayoutLayoutParamsAttributes> {
    type: "coordinatorLayout";
  }}
