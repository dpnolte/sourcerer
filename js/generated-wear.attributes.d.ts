/* generated @ 2018-11-05T13:37:22.172 */
import "./generated-recyclerview.attributes";
import "./generated.attributes";

declare module "LayoutTypes" {
  interface BoxInsetLayoutAttributes extends ViewGroupAttributes {
  }

  interface BoxInsetLayoutLayoutParamsAttributes extends ViewGroupLayoutParamsAttributes {
    boxedEdges?: string;
  }

  interface CircularProgressLayoutAttributes extends FrameLayoutAttributes {
  }

  interface SwipeDismissFrameLayoutAttributes extends FrameLayoutAttributes {
  }

  interface WearableActionDrawerViewAttributes extends WearableDrawerViewAttributes {
    drawerTitle?: string;
  }

  interface WearableDrawerLayoutAttributes extends FrameLayoutAttributes {
  }

  interface WearableDrawerViewAttributes extends FrameLayoutAttributes {
    android_elevation?: string;
    enableAutoPeek?: boolean;
  }

  interface WearableNavigationDrawerViewAttributes extends WearableDrawerViewAttributes {
  }

  interface WearableRecyclerViewAttributes extends RecyclerViewAttributes {
    bezelWidth?: number;
    circularScrollingGestureEnabled?: boolean;
    scrollDegreesPerScreen?: number;
  }
}
